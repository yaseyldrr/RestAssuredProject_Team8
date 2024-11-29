package Movies;

import Utility._01_Parent;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class _06_MoviesTest extends _01_Parent {

    @Test
    public void Details() {
        // https://api.themoviedb.org/3/movie/{{movie_id}}

        given()
                .spec(reqSpec)

                .when()
                .queryParam("api_key", "51e7b410cdede67cb2962d4f9d126c88")
                .get("/movie/" + movieID) // // Movie ID: 27205

                .then()
                .log().body()
                .statusCode(200)
        ;

    }

    @Test
    public void Lists() {
        // https://api.themoviedb.org/3/movie/{{movie_id}}/lists

        given()
                .spec(reqSpec)

                .when()
                .queryParam("api_key", "51e7b410cdede67cb2962d4f9d126c88")
                .get("/movie/" + movieID + "/lists")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }

    @Test
    public void AddRating() {
        // https://api.themoviedb.org/3/movie/{{movie_id}}/rating
        /*
         {"value":8.5}
         */
        Map<String, Object> rating = new HashMap<>();
        rating.put("value", 8.5);

        given()
                .spec(reqSpec)
                .body(rating)

                .when()
                .queryParam("api_key", "51e7b410cdede67cb2962d4f9d126c88")
                .post("/movie/" + movieID + "/rating")

                .then()
                .log().body()
                .statusCode(201)
        ;

    }

    @Test
    public void DeleteRating() {
        // https://api.themoviedb.org/3/movie/{{movie_id}}/rating

        given()
                .spec(reqSpec)

                .when()
                .queryParam("api_key", "51e7b410cdede67cb2962d4f9d126c88")
                .delete("/movie/" + "27205" + "/rating")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }

    // part for getting a list id:
    @Test
    public void CreateList() {
        // https://api.themoviedb.org/3/list?api key=51e7b410cdede67cb2962d4f9d126c88&session
        // id=    "session_id": "b7b9ac277f0fac4db7d864d09f724e75791711d6"
        /*
        {
  "name": "My Favorite Movies",
  "description": "A list of my favorite movies",
  "language": "en"
}
         */
        String sessionId = createSessionId("51e7b410cdede67cb2962d4f9d126c88");
        System.out.println("Session ID: " + sessionId);

        JSONObject list = new JSONObject();
        list.put("name", "My Favorite Movies");
        list.put("description", "A list of my favorite movies");
        list.put("language", "en");

        String listId =
                given()
                        .spec(reqSpec)
                        .body(list.toString())

                        .when()
                        .queryParam("api_key", "51e7b410cdede67cb2962d4f9d126c88")
                        .queryParam("session_id", sessionId)
                        .post("list")

                        .then()
                        .log().all()
                        .statusCode(201)
                        .extract()
                        .path("list_id");

        System.out.println("list_id=" + listId);

    }

    @Test
    public void AddMovie() {
        // https://api.themoviedb.org/3/list/8498552/add_item
         /*
         {
    "media_id": 18

}
          */
        Map<String, Object> Movie = new HashMap<>();
        Movie.put("media_id", 18);

        given()
                .spec(reqSpec)
                .body(Movie)

                .when()
                .queryParam("api_key", "51e7b410cdede67cb2962d4f9d126c88")
                .post("/list/" + "8498552" + "/add_item")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }

}
