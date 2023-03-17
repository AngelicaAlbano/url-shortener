package com.projects.urlshortener.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "available_hashes")
public class HashDTO {
  //  @Indexed(unique = true)
  //  @Column(name = "userId")
  //  private UUID userId;
  private String userId;
  private String hash;
  private Boolean isAvailable;
}
