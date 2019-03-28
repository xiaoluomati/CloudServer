package cn.edu.nju.mqtt;

import cn.edu.nju.config.MqttConfiguration;
import cn.edu.nju.mongo.DataRepository;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MqttReceiveClient {

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private MqttConfiguration configuration;

    private static final Logger LOGGER = LoggerFactory.getLogger(MqttReceiveClient.class);

    private static MqttClient mqttClient;

    @PostConstruct
    public void connect(){
        LOGGER.info(configuration.getHost());
        try {
            mqttClient = new MqttClient(configuration.getHost(), configuration.getClientid(), new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(false);
            options.setUserName(configuration.getUsername());
            options.setPassword(configuration.getPassword().toCharArray());
            options.setConnectionTimeout(configuration.getTimeout());
            options.setKeepAliveInterval(configuration.getKeepalive());
            mqttClient.setCallback(new MqttClientCallBack(dataRepository));
            mqttClient.connect(options);
            mqttClient.subscribe(configuration.getTopic());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
