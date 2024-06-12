package com.example.springboot_mongoatlas.kafka;


import com.example.springboot_mongoatlas.entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumerService.class);

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Task task){
        LOGGER.info(String.format("Json message consumed -> %s", task.toString()));
    }

}
