package com.kafka.application.rest;

import com.kafka.btask.service.CustomerService;
import com.kafka.domain.models.ClientDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {
  private final CustomerService customerService;

  public ClientController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping
  public ClientDto save(@RequestBody ClientDto customer) {
    return this.customerService.save(customer);
  }
}
