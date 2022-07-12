package com.kafka.application.rest;

import com.kafka.btask.interfaces.ClientService;
import com.kafka.domain.models.ClientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/client")
public class ClientController {

  private final ClientService clientService;

  public ClientController( ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping
  public Flux<ClientDto> getListClient() {

    return this.clientService.findAllClient();

  }

  @PostMapping
  public Mono<ClientDto> addClient(@RequestBody Mono<ClientDto> clientDto) {
    return this.clientService.saveClient(clientDto);
  }

  @PutMapping("/{id}")
  public Mono<ClientDto> updateClient(@RequestBody Mono<ClientDto> clientDto,@PathVariable String id){
    return this.clientService.updateClient(clientDto, id);
  }

  @GetMapping("/{id}")
  public Mono<ClientDto> searchClientById(@PathVariable String id){
    return this.clientService.findByIdClient(id);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> deleteClientById(@PathVariable String id){
      return this.clientService.deleteByIdClient(id);
  }
}
