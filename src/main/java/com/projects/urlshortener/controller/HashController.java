package com.projects.urlshortener.controller;

import com.projects.urlshortener.model.ApiResponse;
import com.projects.urlshortener.model.CreateUrlRequest;
import com.projects.urlshortener.model.Hash;
import com.projects.urlshortener.service.HashService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/urls")
public class HashController {

  private final HashService hashService;

  @GetMapping("/populate_available_hashes")
  public void pupulateAvailableHashesCollection() {
    hashService.populate();
  }

  @GetMapping("/{hash}")
  public ResponseEntity<ApiResponse> getHash(@PathVariable("hash") String hash) {

    ApiResponse response =
        ApiResponse.of(
            HttpStatus.OK.value(), "Hash returned!", hashService.getOriginalUrlByHash(hash));
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @PostMapping
  public ResponseEntity<ApiResponse> generateHash(@RequestBody CreateUrlRequest createUrlRequest) {
    ApiResponse response;
    Optional<Hash> hash = hashService.urlHashAlreadyHash(createUrlRequest.getOriginalUrl());
    if (Objects.nonNull(hash) && hash.isPresent()) {
      response =
          ApiResponse.of(
              HttpStatus.BAD_REQUEST.value(),
              "The hash already exists",
              hash.get().getGeneratedHash());
      return ResponseEntity.status(response.getStatus()).body(response);
    }
    response =
        ApiResponse.of(
            HttpStatus.CREATED.value(),
            "Hash generated!",
            hashService.generate(createUrlRequest.getUserId(), createUrlRequest.getOriginalUrl()));

    return ResponseEntity.status(response.getStatus()).body(response);
  }
}
