package com.kafka.btask.service;

import com.kafka.domain.models.ClientDto;
import com.kafka.domain.models.CustomerCreatedEvent;
import com.kafka.domain.models.Event;
import com.kafka.domain.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class CustomerEventsService {

  @Autowired
  private KafkaTemplate<String, Event<?>> producer;

  @Value("${topic.customer.name:estefa}")
  private String topicCustomer;

  public void publish(ClientDto customer) {

    CustomerCreatedEvent created = new CustomerCreatedEvent();
    created.setData(customer);
    created.setId(UUID.randomUUID().toString());
    created.setType(EventType.CREATED);
    created.setDate(new Date());

    this.producer.send(topicCustomer, created);
  }



}
