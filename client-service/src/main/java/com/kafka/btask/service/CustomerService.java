package com.kafka.btask.service;

import com.kafka.domain.models.ClientDto;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final CustomerEventsService customerEventsService;

  public CustomerService(CustomerEventsService customerEventsService) {
    super();
    this.customerEventsService = customerEventsService;
  }

  public ClientDto save(ClientDto customer) {
    System.out.println("Received " + customer);
    this.customerEventsService.publish(customer);
    return customer;

  }

}