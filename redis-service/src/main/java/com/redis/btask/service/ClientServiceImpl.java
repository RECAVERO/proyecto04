package com.redis.btask.service;

import com.redis.application.config.CacheConfig;
import com.redis.btask.interfaces.ClientService;
import com.redis.domain.contract.ClientRepository;
import com.redis.domain.models.ClientDto;
import com.redis.infraestructure.document.Client;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
  private final ClientRepository clientRepository;

  private final WebClient.Builder webClientBuilder;

  public ClientServiceImpl(ClientRepository clientRepository, WebClient.Builder webClientBuilder) {
    this.clientRepository = clientRepository;
    this.webClientBuilder = webClientBuilder;
  }

  @Override
  public List<Client> findAllClient() {
    return this.clientRepository.findAllClient();
  }

  @Override
  @Cacheable(cacheNames = CacheConfig.USER_CACHE, unless = "#result == null")
  public Client saveClient(Client client) {

    return this.clientRepository.saveClient(client);

  }

  @Override
  @CacheEvict(cacheNames = CacheConfig.USER_CACHE, key = "#id")
  public void deleteByIdClient(String id) {

    this.clientRepository.deleteByIdClient(id);
  }

  @Override
  @CachePut(cacheNames = CacheConfig.USER_CACHE, key = "#id", unless = "#result == null")
  public Client updateClient(Client client, String id) {
    return this.clientRepository.updateClient(client, id);
  }

  @Override
  @Cacheable(cacheNames = CacheConfig.USER_CACHE, unless = "#result == null")
  public Optional<Client> findByIdClient(String id) {
    return this.clientRepository.findByIdClient(id);
  }
}
