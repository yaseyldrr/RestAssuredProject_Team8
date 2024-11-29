package Utility;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class _01_Parent {

    protected RequestSpecification reqSpec;
    protected String sessionId;
    public int movieID = 0;
    // public int listID = 0;

    @BeforeClass
    public void setup() {

        baseURI = "https://api.themoviedb.org/3/";

        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1MWU3YjQxMGNkZWRlNjdjYjI5NjJkNGY5ZDEyNmM4OCIsIm5iZiI6MTczMjg3MjcyOC4zNjExNTY1LCJzdWIiOiI2NzQxN2Q4YmFhYjcyMmJmYTdjOGRjYzYiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.sfRaWnB69KGYlr3GHKCy7ZFgxprSGHuXuNG0Hse25yM")
                .setContentType(ContentType.JSON)
                .build();

        String requestToken = createRequestToken();
        validateRequestTokenWithLogin(requestToken);
        sessionId = createSessionId(requestToken);

        System.out.println("Session ID: " + sessionId);
    }

    public String createRequestToken() {
        return given()
                .spec(reqSpec)
                .when()
                .get("authentication/token/new")
                .then()
                .statusCode(200)
                .extract()
                .path("request_token");
    }

    public void validateRequestTokenWithLogin(String requestToken) {
        given()
                .spec(reqSpec)
                .body("{ \"username\": \"team.8\", \"password\": \"technostudy\", \"request_token\": \"" + requestToken + "\" }")
                .when()
                .post("authentication/token/validate_with_login")
                .then()
                .statusCode(200);
    }

    public String createSessionId(String requestToken) {
        return given()
                .spec(reqSpec)
                .body("{ \"request_token\": \"" + requestToken + "\" }")
                .when()
                .post("authentication/session/new")
                .then()
                .statusCode(200)
                .extract()
                .path("session_id");
    }
}