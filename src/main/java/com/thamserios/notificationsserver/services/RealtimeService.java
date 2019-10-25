package com.thamserios.notificationsserver.services;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class RealtimeService {
    private static final String SYSTEM_UPDATES_TOPIC = "sys_updates";
    private static final String LOCATION_UPDATES_TOPIC = "location_updates";
    private IMqttClient client;

    public RealtimeService(IMqttClient client){
        this.client = client;
        try {
            connect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void connect() throws MqttException {
        /*
        maxConnectionRetries = 20160; // 336 hours or two weeks
        maxRetryDelay = 60000; // retry each minute
        maxOfflineQueueSize = 303840; // max possible messages in 1 week
        numOfClientThreads = 2;
        keepAliveInterval = 300000; // Once every 5 minutes
         */
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setKeepAliveInterval(300000);
        options.setConnectionTimeout(60);
        client.connect(options);
    }

    public void sendMessage(String message) throws MqttException {
        byte[] payload = message.getBytes();
        MqttMessage mqttMessage = new MqttMessage(payload);
        client.publish(SYSTEM_UPDATES_TOPIC,mqttMessage);
    }

    public void locationUpdates(String message) throws MqttException {
        byte[] payload = message.getBytes();
        MqttMessage mqttMessage = new MqttMessage(payload);
        client.publish(LOCATION_UPDATES_TOPIC,mqttMessage);
    }
}
