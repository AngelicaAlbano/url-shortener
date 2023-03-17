package com.projects.urlshortener.service;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.projects.urlshortener.model.Hash;
import com.projects.urlshortener.model.HashDTO;
import com.projects.urlshortener.model.HashRepository;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@Service
public class HashService {
  private final HashRepository repository;
  private final MongoTemplate mongoTemplate;
  private static final byte HASH_LENGTH = 6;

  public String getOriginalUrlByHash(String hash) {
    return repository.findByGeneratedHash(hash).getOriginalUrl();
  }

  public String generate(String userId, String originalUrl) {

    Document d =
        mongoTemplate
            .getCollection("available_hashes")
            .findOneAndUpdate(Filters.eq("isAvailable", true), Updates.set("isAvailable", false));

    Hash hash = new Hash();
    hash.setOriginalUrl(originalUrl);
    hash.setGeneratedHash(d.get("hash").toString());
    hash.setCreatedAt(LocalDate.now());
    hash.setUserId(userId);
    repository.save(hash);

    return hash.getGeneratedHash();
  }

  public void populate() {
    Long i;
    // double stop_value = Math.pow(64, HASH_LENGTH);

    for (i = 0L; i < 20; i++) {
      Random random = ThreadLocalRandom.current();
      byte[] randomBytes = new byte[6];
      random.nextBytes(randomBytes);
      String s = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
      HashDTO hash = new HashDTO();
      hash.setUserId(UUID.randomUUID().toString());
      hash.setHash(s);
      hash.setIsAvailable(true);
      mongoTemplate.insert(hash, "available_hashes");
    }
  }

  public Optional<Hash> urlHashAlreadyHash(String url) {
    return repository.findByOriginalUrl(url);
  }
}
