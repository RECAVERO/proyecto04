package com.redis.infraestructure.repository;

import com.redis.domain.contract.ClientRepository;
import com.redis.domain.models.ClientDto;
import com.redis.infraestructure.document.Client;
import com.redis.infraestructure.mongodb.ClientRepositoryMongo;
import com.redis.utils.convert.Convert;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
  private final ClientRepositoryMongo clientRepositoryMongo;

  public ClientRepositoryImpl(ClientRepositoryMongo clientRepositoryMongo) {
    this.clientRepositoryMongo = clientRepositoryMongo;
  }


  @Override
  public List<Client> findAllClient() {
    return this.clientRepositoryMongo.findAll();
  }

  @Override
  public Client saveClient(Client client) {
    return this.clientRepositoryMongo.save(client);
  }

  @Override
  public Optional<Client> findByIdClient(String id) {
    return this.clientRepositoryMongo.findById(id);
  }

  @Override
  public Client updateClient(Client client, String id) {
    Optional<Client> clientExistente=this.clientRepositoryMongo.findById(id);
    return this.clientRepositoryMongo.save(client);
  }

  @Override
  public void deleteByIdClient(String id) {
    this.clientRepositoryMongo.deleteById(id);
  }
}
