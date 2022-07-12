package com.kafka.infraestructure.repository;

import com.kafka.domain.contract.ClientRepository;
import com.kafka.domain.models.ClientDto;
import com.kafka.infraestructure.mongodb.ClientRepositoryMongodb;
import com.kafka.utils.convert.Convert;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
  private final ClientRepositoryMongodb clientRepositoryMongodb;

  public ClientRepositoryImpl(ClientRepositoryMongodb clientRepositoryMongodb) {
    this.clientRepositoryMongodb = clientRepositoryMongodb;
  }

  @Override
  public Flux<ClientDto> findAllClient() {
    return this.clientRepositoryMongodb.findAll().map(Convert::entityToDto);
  }

  @Override
  public Mono<ClientDto> saveClient(Mono<ClientDto> clientDto) {
    return clientDto.map(Convert::dtoToEntity)
        .flatMap(this.clientRepositoryMongodb::insert)
        .map(Convert::entityToDto);
  }

  @Override
  public Mono<ClientDto> findByIdClient(String id) {
    return this.clientRepositoryMongodb.findById(id)
        .map(Convert::entityToDto).defaultIfEmpty(new ClientDto());
  }

  @Override
  public Mono<ClientDto> updateClient(Mono<ClientDto> clientDto, String id) {
    return  this.clientRepositoryMongodb.findById(id)
        .flatMap(p -> clientDto.map(Convert::dtoToEntity)
            .doOnNext(e -> e.setId(id)))
        .flatMap(this.clientRepositoryMongodb::save)
        .map(Convert::entityToDto);
  }

  @Override
  public Mono<Void> deleteByIdClient(String id) {
    return this.clientRepositoryMongodb.deleteById(id);
  }
}
