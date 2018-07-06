package com.zzc.test.springbootcanal.core;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.InvalidProtocolBufferException;
import com.zzc.test.springbootcanal.annotation.CanalCatcher;
import com.zzc.test.springbootcanal.enums.ConnectionModel;
import com.zzc.test.springbootcanal.listener.CanalListener;
import com.zzc.test.springbootcanal.properties.CanalProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengzechao
 * @date 2018/7/5
 * Email ooczzoo@gmail.com
 */

public class CanalClientManager implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(CanalClientManager.class);
    /**
     * 全局配置
     */
    private CanalProperties globalConfig;

    private ConcurrentHashMap<Integer, CanalClientTask> clients = new ConcurrentHashMap<>();

    private Integer count = new Integer(0);

    private ThreadPoolExecutor clientThreadPool = null;

    public CanalClientManager() {

    }

    public CanalClientManager(CanalProperties globalConfig) {
        this.globalConfig = globalConfig;

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        clientThreadPool = new ThreadPoolExecutor(globalConfig.getCoreThreadSize(),
                globalConfig.getMaxThreadSize(),
                globalConfig.getKeepAliveTime(),
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>());
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(CanalCatcher.class);
        Iterator var1 = beansWithAnnotation.entrySet().iterator();
        while (var1.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry) var1.next();
            Object bean = entry.getValue();
            if (CanalListener.class.isAssignableFrom(bean.getClass())) {
                CanalCatcher var2 = bean.getClass().getDeclaredAnnotation(CanalCatcher.class);
                //解析元数据
                ClientConfig clientConfig = ClientConfig.parse(var2, globalConfig);
                build(clientConfig, (CanalListener) bean);
            }

        }

    }


    public void build(ClientConfig config, CanalListener canalListener) {
        CanalConnector canalConnector = null;
        InetSocketAddress inetSocketAddress = new InetSocketAddress(config.getHost(), config.getPort());
        if (Objects.equals(config.getConnectionModel(), ConnectionModel.SIMPLE)) {
            canalConnector = CanalConnectors.newSingleConnector(inetSocketAddress,
                    config.getDestination(),
                    config.getUsername(),
                    config.getPassword());
            count += 1;
            CanalClientTask task = new CanalClientTask(canalConnector, config, canalListener);
            clientThreadPool.execute(task);
            clients.put(count, task);
        } else {
            //集群模式 暂时跳过
//            canalConnector = CanalConnectors.newClusterConnector()

        }

    }


    private class CanalClientTask implements Runnable {
        /**
         * 运行状态
         */
        public volatile boolean running = true;

        private volatile long idleSleepTime;
        private CanalConnector canalConnector;
        private ClientConfig clientConfig;
        private CanalListener canalListener;

        public CanalClientTask(CanalConnector canalConnector, ClientConfig clientConfig, CanalListener canalListener) {
            this.canalConnector = canalConnector;
            this.clientConfig = clientConfig;
            this.canalListener = canalListener;
            this.idleSleepTime = clientConfig.getIdleSleepTime();
        }

        public void mainLoop() {
            try {
                int batchSize = clientConfig.getBatchSize();
                canalConnector.connect();
                canalConnector.subscribe(clientConfig.getSubsribePattern());
                while (running) {
                    //获取指定数量的数据
                    Message message = canalConnector.getWithoutAck(batchSize);
                    long batchId = message.getId();
                    int size = message.getEntries().size();
                    if (batchId == -1 || size == 0) {
                        try {
                            Thread.sleep(idleSleepTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //消息处理

                        processEntries(message.getEntries(), canalListener);

                    }
                    // 提交确认
                    canalConnector.ack(batchId);
                    // 处理失败, 回滚数据
                    //canalConnector.rollback(batchId);
                }
            } finally {
                canalConnector.disconnect();
            }
        }
        @Override
        public void run() {
            //主循环
            mainLoop();
        }
    }


    public void processEntries(List<CanalEntry.Entry> entries, CanalListener canalListener) {

        for (int i = 0; i < entries.size(); i++) {
            CanalEntry.Entry entry = entries.get(i);
            //获取类型
            CanalEntry.EntryType entryType = entry.getEntryType();
            if (log.isDebugEnabled()) {
                log.debug(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
                        entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                        entry.getHeader().getSchemaName(), entry.getHeader().getTableName(), entryType));
            }

            //跳过事务
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN
                    || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {

                continue;
            }
            CanalEntry.RowChange rowChange = null;
            try {
                //解析成行数据对象
                rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                //获得事件类型
                CanalEntry.EventType eventType = rowChange.getEventType();
                if (eventType == CanalEntry.EventType.DELETE) {
                    canalListener.onDelete(entry,rowChange);
                } else if (eventType == CanalEntry.EventType.INSERT) {
                    canalListener.onInsert(entry,rowChange);
                } else if (eventType == CanalEntry.EventType.UPDATE) {
                    canalListener.onUpdate(entry,rowChange);
                } else {
                    //DDL语句
                    if (rowChange.hasSql()) {
                        canalListener.onDDL(entry,rowChange);
                    }
                }
            } catch (InvalidProtocolBufferException e) {
                canalListener.onException(entry,rowChange);
            }

        }

    }


}
