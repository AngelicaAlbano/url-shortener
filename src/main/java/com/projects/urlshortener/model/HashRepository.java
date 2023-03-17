package com.projects.urlshortener.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HashRepository extends MongoRepository<Hash, String> {

  Optional<Hash> findByOriginalUrl(String originalUrl);

  Hash findByGeneratedHash(String hash);
}
