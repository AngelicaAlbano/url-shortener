package com.projects.urlshortener;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UrlshortenerApplicationTests {

  //  @Test
  //  void contextLoads() {}

  private static final String HOST = "localhost";
  private static final String PORT = "27017";
  private static final String DB = "db_url_shortener";
  private static final String USER = "admin";
  private static final String PASS = "mongod";

  // test cases
  private void assertInsertSucceeds(ConfigurableApplicationContext context) {
    String name = "A";

    MongoTemplate mongo = context.getBean(MongoTemplate.class);
    Document doc = Document.parse("{\"name\":\"" + name + "\"}");
    Document inserted = mongo.insert(doc, "items");

    assertNotNull(inserted.get("_id"));
    assertEquals(inserted.get("name"), name);
  }

  @Test
  public void whenPropertiesConfig_thenInsertSucceeds() {
    SpringApplicationBuilder app = new SpringApplicationBuilder(UrlshortenerApplication.class);
    app.run();

    assertInsertSucceeds(app.context());
  }
}
