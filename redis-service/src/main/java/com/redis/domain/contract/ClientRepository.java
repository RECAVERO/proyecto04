package com.redis.domain.contract;

import com.redis.domain.models.ClientDto;
import com.redis.infraestructure.document.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
  List<Client> findAllClient();

  Client saveClient(Client clientDto);

  Optional<Client> findByIdClient(String id);

  Client updateClient(Client clientDto, String id);

  void deleteByIdClient(String id);
}
