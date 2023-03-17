package com.projects.urlshortener.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUrlRequest {
  //  private UUID userId;
  private String userId;
  private String originalUrl;
}
