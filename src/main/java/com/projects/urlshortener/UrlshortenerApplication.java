package com.projects.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.projects.urlshortener.model")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class UrlshortenerApplication {

  public static void main(String[] args) {

    SpringApplication.run(UrlshortenerApplication.class, args);
  }
}
