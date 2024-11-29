package MovieList;

import Utility._01_Parent;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class _03_MovieListTest extends _01_Parent {
    @Test
    public void NowPlaying() {
        // https://api.themoviedb.org/3/movie/now_playing

        given()
                .spec(reqSpec)

                .when()
                .get("/movie/now_playing")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }
    @Test
    public void Popular() {
        // https://api.themoviedb.org/3/movie/top_rated

        given()
                .spec(reqSpec)

                .when()
                .get("/movie/top_rated")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }
    @Test
    public void Upcoming() {
        // https://api.themoviedb.org/3/movie/upcoming

        given()
                .spec(reqSpec)

                .when()
                .get("/movie/upcoming")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }
}
