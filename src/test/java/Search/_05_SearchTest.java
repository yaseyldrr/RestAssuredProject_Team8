package Search;

import Utility._01_Parent;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class _05_SearchTest extends _01_Parent {

    @Test
    public void Movie() {
        // https://api.themoviedb.org/3/search/movie?query=inception

        movieID =
                given()
                        .spec(reqSpec)

                        .when()
                        .queryParam("api_key", "51e7b410cdede67cb2962d4f9d126c88")
                        .queryParam("query", "inception")
                        .get("/search/movie")

                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract()
                        .path("results[0].id")
        ;
        System.out.println("Movie ID: " + movieID); // Movie ID: 27205

    }

    @Test
    public void TV() {
        // https://api.themoviedb.org/3/search/tv?api_key=51e7b410cdede67cb2962d4f9d126c88&query=breaking bad

        given()
                .spec(reqSpec)

                .when()
                .queryParam("api_key", "51e7b410cdede67cb2962d4f9d126c88")
                .queryParam("query", "breaking bad")
                .get("/search/tv")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }
    @Test
    public void Person() {
        // https://api.themoviedb.org/3/search/person?query=leonardo dicaprio&api_key=51e7b410cdede67cb2962d4f9d126c88

        given()
                .spec(reqSpec)

                .when()
                .queryParam("api_key", "51e7b410cdede67cb2962d4f9d126c88")
                .queryParam("query", "leonardo dicaprio")
                .get("/search/person")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }
    @Test
    public void Keyword() {
        // https://api.themoviedb.org/3/search/keyword?query=action&api_key=51e7b410cdede67cb2962d4f9d126c88

        given()
                .spec(reqSpec)

                .when()
                .queryParam("api_key", "51e7b410cdede67cb2962d4f9d126c88")
                .queryParam("query", "action")
                .get("/search/keyword")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }
}
