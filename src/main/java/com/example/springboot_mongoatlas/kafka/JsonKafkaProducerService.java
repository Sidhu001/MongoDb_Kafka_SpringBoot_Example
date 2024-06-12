package com.example.springboot_mongoatlas.kafka;

import com.example.springboot_mongoatlas.entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducerService.class);

    @Autowired
    private KafkaTemplate<String, Task> kafkaTemplate;

    public void sendMessage(Task task){

        LOGGER.info("Message sent: {} ", task.toString());
        Message<Task> message = MessageBuilder.withPayload(task)
                .setHeader(KafkaHeaders.TOPIC, "kafka-guides-json")
                .build();
        kafkaTemplate.send(message);
    }

}
