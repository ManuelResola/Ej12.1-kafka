package com.formacionback.Ej12.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class KafkaMessageProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${message.topic.name:profesorp}")
    private String topicName;
@Bean
    public void sendMessage(String topic,String message) {
        if (topic==null || topic.trim().equals(""))
            topic=topicName;
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {System.err.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }
}