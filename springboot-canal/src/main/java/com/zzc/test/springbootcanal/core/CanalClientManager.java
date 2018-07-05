package com.zzc.test.springbootcanal.core;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.google.common.collect.Maps;
import com.zzc.test.springbootcanal.annotation.CanalCatcher;
import com.zzc.test.springbootcanal.enums.ConnectionModel;
import com.zzc.test.springbootcanal.listener.CanalListener;
import com.zzc.test.springbootcanal.properties.CanalProperties;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.Iterator;
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
            if (CanalListener.class.isAssignableFrom(bean.getClass())){
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
            CanalClientTask task = new CanalClientTask(canalConnector,config);
            clients.put(count,task);
        } else {
            //集群模式 暂时跳过
//            canalConnector = CanalConnectors.newClusterConnector()

        }

    }


    private static class CanalClientTask implements Runnable {
        /**
         * 运行状态
         */
        public volatile boolean running = true;
        private CanalConnector canalConnector;
        private ClientConfig clientConfig;

        public CanalClientTask(CanalConnector canalConnector, ClientConfig clientConfig) {
            this.canalConnector = canalConnector;
            this.clientConfig = clientConfig;
        }

        public void mainLoop() {
            canalConnector.connect();
            canalConnector.subscribe(clientConfig.getSubsribePattern());

            while (running) {



            }

        }

        @Override
        public void run() {
            mainLoop();
        }
    }


}
