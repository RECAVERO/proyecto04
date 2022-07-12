package com.kafka.btask.services;

import com.kafka.btask.interfaces.ClientService;
import com.kafka.domain.contract.ClientRepository;
import com.kafka.domain.models.ClientDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {
  private final ClientRepository clientRepository;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public ClientServiceImpl(ClientRepository clientRepository, KafkaTemplate<String, String> kafkaTemplate) {
    this.clientRepository = clientRepository;
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public Flux<ClientDto> findAllClient() {
    return this.clientRepository.findAllClient();
  }

  @Override
  public Mono<ClientDto> saveClient(Mono<ClientDto> clientDto) {
    return this.clientRepository.saveClient(clientDto).flatMap(client->{
      System.out.println("Received " + client);
      this.kafkaTemplate.send("topic-1","Se Inserto el Cliente :" + client);
      return Mono.just(client);
    });
  }

  @Override
  public Mono<ClientDto> findByIdClient(String id) {
    return this.clientRepository.findByIdClient(id);
  }

  @Override
  public Mono<ClientDto> updateClient(Mono<ClientDto> clientDto, String id) {
    return this.clientRepository.updateClient(clientDto, id)
        .flatMap(client->{
      System.out.println("Received " + client);
      this.kafkaTemplate.send("topic-1","Se Actualizo el Cliente :" + client);
      return Mono.just(client);
    });
  }

  @Override
  public Mono<Void> deleteByIdClient(String id) {
    return this.clientRepository.findByIdClient(id).flatMap(client->{
      if(client != null){
        this.sendKafka(id);
        return this.clientRepository.deleteByIdClient(id);
      }else{
        return this.clientRepository.deleteByIdClient("-1");
      }

    });
  }
  private void sendKafka(String id){
    this.kafkaTemplate.send("topic-1","Se Elimino el Cliente :" + id);
  }
}
