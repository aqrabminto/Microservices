import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class patientIntegrationTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:4004";
    }

    @Test
    public void shouldReturnPatientWithValidToken() {

        String loginPayload = """
                {
                "email":"testuser@test.com",
                "password":"password123"
                }
                """;
        String token = given().
                contentType("application/json").
                body(loginPayload).
                when().
                post("/auth/login").//act
                 then().
                statusCode(200).//assert
                extract().
                jsonPath()
                .get("token");
        System.out.println(token);

        given().header("Authorization","Bearer " + token)
                .when()
                .get("/api/patients")
                .then()
                .statusCode(200)
                .body("patients", notNullValue());


    }
}
