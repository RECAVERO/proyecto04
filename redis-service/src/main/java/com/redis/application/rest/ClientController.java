package com.redis.application.rest;

import com.redis.btask.interfaces.ClientService;
import com.redis.infraestructure.document.Client;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {
  private final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }
  @GetMapping
  public List<Client> getListClient() {
    return this.clientService.findAllClient();
  }

  @GetMapping("/{id}")
  public Optional<Client> getListClient(@PathVariable String id) {
    return this.clientService.findByIdClient(id);
  }

  @PostMapping
  public Client addClient(@RequestBody Client client) {
    return this.clientService.saveClient(client);
  }


  @DeleteMapping("/{id}")
  public String deleteClient(@PathVariable String id) {
     this.clientService.deleteByIdClient(id);
     return "Se booroo";
  }

  @PutMapping("/{id}")
  public Client updateClient(@RequestBody Client cli, @PathVariable String id) {
    return this.clientService.updateClient(cli, id);
  }
}
