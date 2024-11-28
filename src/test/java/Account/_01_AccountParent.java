package Account;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class _01_AccountParent {

    //Faker randomGenerator = new Faker();
    RequestSpecification reqSpec;

    @BeforeClass
    public void Setup() {
        // TMDB Base URI
        baseURI = "https://api.themoviedb.org/3/";

        // Common Request Specification
        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer <your_access_token>")
                .setContentType(ContentType.JSON)
                .build();

        // Step 1: Create Request Token
        String requestToken =
                given()
                        .spec(reqSpec)
                        .when()
                        .get("authentication/token/new")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("request_token");

        System.out.println("Request Token: " + requestToken);

        // Step 2: Validate Token with Login
        given()
                .spec(reqSpec)
                .body("{ \"username\": \"team.8\", \"password\": \"technostudy\", \"request_token\": \"" + requestToken + "\" }")
                .when()
                .post("authentication/token/validate_with_login")
                .then()
                .statusCode(200);

        // Step 3: Create Session ID
        String sessionId =
                given()
                        .spec(reqSpec)
                        .body("{ \"request_token\": \"" + requestToken + "\" }")
                        .when()
                        .post("authentication/session/new")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("session_id");

        System.out.println("Session ID: " + sessionId);
    }
}


