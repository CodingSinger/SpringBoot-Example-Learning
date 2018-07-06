package com.zzc.test.springbootcanal.core;

import com.zzc.test.springbootcanal.annotation.CanalCatcher;
import com.zzc.test.springbootcanal.enums.ConnectionModel;
import com.zzc.test.springbootcanal.properties.CanalProperties;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author zhengzechao
 * @date 2018/7/5
 * Email ooczzoo@gmail.com
 */
public class ClientConfig {


    private String host;

    private Integer port;

    private String destination;

    private String username;

    private String password;

    private String subsribePattern;

    private Long idleSleepTime;

    private int timeout;

    private int batchSize;

    private ConnectionModel connectionModel;

    private ClientConfig() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubsribePattern() {
        return subsribePattern;
    }

    public void setSubsribePattern(String subsribePattern) {
        this.subsribePattern = subsribePattern;
    }

    public Long getIdleSleepTime() {
        return idleSleepTime;
    }

    public void setIdleSleepTime(Long idleSleepTime) {
        this.idleSleepTime = idleSleepTime;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public ConnectionModel getConnectionModel() {
        return connectionModel;
    }

    public void setConnectionModel(ConnectionModel connectionModel) {
        this.connectionModel = connectionModel;
    }

    /**
     * 解析客户端配置
     *
     * @param canalCatcher
     * @param globalProperties
     * @return
     */
    public static ClientConfig parse(CanalCatcher canalCatcher, CanalProperties globalProperties) {
        ClientConfig clientConfig = new ClientConfig();
        try {

            String value = canalCatcher.value();
            int colon = value.indexOf(":");
            String host = value.substring(0, colon);
            if (StringUtils.hasText(host)) {
                clientConfig.setHost(host);
            }
            int strip = value.indexOf("-");
            String port = value.substring(colon + 1, strip);
            if (StringUtils.hasText(port)) {
                clientConfig.setPort(Integer.valueOf(port));
            }
            String destination = value.substring(strip + 1);
            if (StringUtils.hasText(destination)) {
                clientConfig.setDestination(destination);
            }
            String idleSleepTime = canalCatcher.idleSleepTime();
            if (Objects.equals("", idleSleepTime)) {
                clientConfig.setIdleSleepTime(globalProperties.getGlobalIdleSleepTime());
            }
            clientConfig.setUsername(canalCatcher.username());
            clientConfig.setPassword(canalCatcher.password());
            clientConfig.setSubsribePattern(canalCatcher.pattern());
            clientConfig.setBatchSize(canalCatcher.batchSize());
            clientConfig.setTimeout(canalCatcher.timeout());
            clientConfig.setConnectionModel(canalCatcher.model());

        } catch (NumberFormatException e) {

        }
        return clientConfig;

    }


}
