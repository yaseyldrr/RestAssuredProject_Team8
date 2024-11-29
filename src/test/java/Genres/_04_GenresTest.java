package Genres;

import Utility._01_Parent;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class _04_GenresTest extends _01_Parent {
    @Test
    public void MovieList() {
        // https://api.themoviedb.org/3/genre/movie/list

        given()
                .spec(reqSpec)

                .when()
                .get("/genre/movie/list")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }
     @Test
    public void TVList() {
        // https://api.themoviedb.org/3/genre/tv/list

        given()
                .spec(reqSpec)

                .when()
                .get("/genre/tv/list")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }
}
