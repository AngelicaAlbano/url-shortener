package com.projects.urlshortener.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "hashes")
public class Hash {

  //  @Indexed(unique = true)
  //  @Column(name = "userId")
  //  private UUID userId;
  private String userId;
  private String originalUrl;
  private String generatedHash;
  private LocalDate createdAt;
}
