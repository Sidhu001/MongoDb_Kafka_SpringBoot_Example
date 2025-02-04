package com.example.springboot_mongoatlas.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic javaGuidesTopic(){
        return TopicBuilder.name("kafka-guides").build();
    }

    @Bean
    public NewTopic javaJsonGuidesTopic(){
        return TopicBuilder.name("kafka-guides-json").build();
    }



}
