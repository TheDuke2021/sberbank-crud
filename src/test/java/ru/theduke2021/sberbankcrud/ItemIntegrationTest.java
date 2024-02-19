package ru.theduke2021.sberbankcrud;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ItemIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Value("${api.default_per_page}")
    private int DEFAULT_PER_PAGE;


    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16.1-alpine"
    );

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
        RestAssured.reset();
    }

    @BeforeEach
    void beforeEach() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }


    // If pagination parameters are not explicitly set by the client - the API
    // should fall back to the default pagination parameters
    @Test
    void shouldFallBackToDefaultPaginationParameters() {
        when()
            .get("/items")
            .then()
            .statusCode(200)
            .body("$", hasSize(DEFAULT_PER_PAGE));
    }

    @Test
    void shouldReturnCorrectItemById() {
        when()
            .get("/items/{id}", 5)
            .then()
            .statusCode(200)
            .body("id", equalTo(5))
            .body("title", equalTo("Pepper - Chillies, Crushed"))
            .body("quantity", equalTo(757));
    }

    @Test
    void shouldReturnBadRequestIfTitleIsBlank() {
        String payload = """
            {
                "title": "    ",
                "quantity": 16
            }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(payload)
            .when()
            .put("/items/{id}", 1)
            .then()
            .statusCode(400)
            .body("message", equalTo("Title of an item must not be empty!"));

        given()
            .contentType(ContentType.JSON)
            .body(payload)
            .when()
            .post("/items")
            .then()
            .statusCode(400)
            .body("message", equalTo("Title of an item must not be empty!"));
    }

    @Test
    void shouldReturnBadRequestIfQuantityIsNegative() {
        String payload = """
            {
                "title": "Cherry",
                "quantity": -1
            }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(payload)
            .when()
            .put("/items/{id}", 1)
            .then()
            .statusCode(400)
            .body("message", equalTo("Quantity of an item must not be negative!"));

        given()
            .contentType(ContentType.JSON)
            .body(payload)
            .when()
            .post("/items")
            .then()
            .statusCode(400)
            .body("message", equalTo("Quantity of an item must not be negative!"));
    }

    @Test
    void shouldCreateItemOnPostAndReturnIt() {
        String payload = """
            {
                "title": "Kebab",
                "quantity": 50
            }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(payload)
            .when()
            .post("/items")
            .then()
            .statusCode(201)
            .body("title", equalTo("Kebab"))
            .body("quantity", equalTo(50));
    }

}
