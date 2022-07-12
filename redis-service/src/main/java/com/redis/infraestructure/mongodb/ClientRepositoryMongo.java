package com.redis.infraestructure.mongodb;

import com.redis.infraestructure.document.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClientRepositoryMongo extends MongoRepository<Client, String> {
}
