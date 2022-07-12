package com.kafka.infraestructure.mongodb;

import com.kafka.infraestructure.document.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClientRepositoryMongodb extends ReactiveMongoRepository<Client, String> {
}
