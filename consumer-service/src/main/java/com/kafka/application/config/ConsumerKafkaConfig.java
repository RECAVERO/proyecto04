package com.kafka.application.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ConsumerKafkaConfig {
  private final KafkaProperties kafkaProperties;

  public ConsumerKafkaConfig(KafkaProperties kafkaProperties) {
    this.kafkaProperties = kafkaProperties;
  }
  @Bean
  public ConsumerFactory<String, String> consumerFactory(){
    Map<String, Object> configs = new HashMap<String, Object>();
    configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
    configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    return new DefaultKafkaConsumerFactory<>(configs);
  }
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String,String> concurrentKafkaListenerContainerFactory(){
    ConcurrentKafkaListenerContainerFactory<String,String> factory=new ConcurrentKafkaListenerContainerFactory<String,String>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}