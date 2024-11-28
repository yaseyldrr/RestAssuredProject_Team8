package Account;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class _02_AccountTest extends _01_AccountParent {
    @Test
    public void LoginTest() {

        given()
                .spec(reqSpec)

                .when()
                .get("login?language=tr")

                .then()
                .log().body()
                .statusCode(403)
        ;
    }
}
