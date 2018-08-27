package com.rrl.wms.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String payload) {
        System.out.println("sending payload='{}' to topic='{}'" + payload +"  "+topic);
        kafkaTemplate.send(topic, payload);
    }
}