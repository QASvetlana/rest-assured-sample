import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresTests {
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    void createUser() {
        String data = "{\"name\": \"morpheus\",\"job\": \"leader\"}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("name", is("morpheus"));

    }

    @Test
    void singleResourceNotFound() {
        given()
                .when()
                .get("/api/unknown/23")
                .then()
                .statusCode(404)
                .body(is("{}"));

    }
}