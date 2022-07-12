package com.kafka.application.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class testListener {

  @KafkaListener(topics = "topic-1", groupId = "group-1")
  public void listen(List<String> messages) {
    messages.forEach((client)->{
      System.out.println(client);
    });

    log.info("Thread: {} Messages: {}", Thread.currentThread().getId(), messages);
  }
}
