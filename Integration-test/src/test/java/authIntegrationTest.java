import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class authIntegrationTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:4004";

    }
    @Test
    public void shouldNotReturnOKWithValidToken() {
//        Arrange
//        Act
//        assert
        String loginPayload= """
                {
                "email":"testuser@test.com",
                "password":"password123"
                }
                """;
        Response response=given().
                contentType("application/json").
                body(loginPayload).
                when().
                post("/auth/login").//act
                then().
                statusCode(200).//assert
                body("token", notNullValue()).
                extract().
                response();

        System.out.println("Generated token:"+response.jsonPath().getString("token"));

    }

    @Test
    public void shouldReturnOKWithInValidToken() {
//        Arrange
//        Act
//        assert
        String loginPayload= """
                {
                "email":"testinvalid@test.com",
                "password":"password123"
                }
                """;
        given().
                contentType("application/json").
                body(loginPayload).
                when().
                post("/auth/login").
                then().
                statusCode(401);


    }
}
