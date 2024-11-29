package Account;

import Utility._01_Parent;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class _02_AccountTest extends _01_Parent {
    int accountId = 0;


    @Test
    public void LoginTest() {

        Map<String, String> loginInfo = new HashMap<>();
        loginInfo.put("username", "team.8");
        loginInfo.put("password", "technostudy");
        loginInfo.put("request_token", createRequestToken());

        given()
                .spec(reqSpec)
                .body(loginInfo)

                .when()
                .post("authentication/token/validate_with_login")

                .then()
                .log().body()
                .statusCode(200)
        ;
    }

    @Test
    public void Account() {

        accountId =
                given()
                        .spec(reqSpec)
                        .queryParam("session_id", sessionId)

                        .when()
                        .get("account")

                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract()
                        .path("id");
        System.out.println("Account ID: " + accountId);  // Account ID: 21647182

    }

    @Test
    public void AddFavorite() {
        /*
         {
    "media_type":"movie",
    "media_id":550,
    "favorite":true
 }
         */
        JSONObject favorite = new JSONObject();
        favorite.put("media_type", "movie");
        favorite.put("media_id", 550);
        favorite.put("favorite", true);
        given()
                .spec(reqSpec)
                .body(favorite.toString())

                .when()
                .post("account/" + accountId + "/favorite")

                .then()
                .log().body()
                .statusCode(201)
        ;
    }

    @Test
    public void AddToWatchList() {
        /*
        {
    "media_type":"movie"
    ,"media_id":11,
    "watchlist":true

         */
        JSONObject favorite = new JSONObject();
        favorite.put("media_type", "movie");
        favorite.put("media_id", 11);
        favorite.put("watchlist", true);
        given()
                .spec(reqSpec)
                .body(favorite.toString())

                .when()
                .post("account/" + accountId + "/watchlist")

                .then()
                .log().body()
                .statusCode(201)
        ;
    }

    @Test
    public void FavoriteMovies() {
        // https://api.themoviedb.org/3/account/%7baccount_id%7d/favorite/movies

        given()
                .spec(reqSpec)

                .when()
                .get("account/" + accountId + "/favorite/movies")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }
    @Test
    public void FavoriteTV() {
        //https://api.themoviedb.org/3/account/{{account_id}}/favorite/tv

        given()
                .spec(reqSpec)

                .when()
                .get("account/" + "21647182" + "/favorite/tv")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }
    @Test
    public void RatedMovies() {
        //https://api.themoviedb.org/3/account/{{account_id}}/rated/movies

        given()
                .spec(reqSpec)

                .when()
                .get("account/" + accountId + "/rated/movies")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }
    @Test
    public void RatedTV() {
        //https://api.themoviedb.org/3/account/{{account_id}}/rated/tv

        given()
                .spec(reqSpec)

                .when()
                .get("account/" + accountId + "/rated/tv")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }
    @Test
    public void WatchlistMovies() {
        //https://api.themoviedb.org/3/account/{{account_id}}/watchlist/movies

        given()
                .spec(reqSpec)

                .when()
                .get("account/" + accountId + "/watchlist/movies")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }
    @Test
    public void WatchlistTV() {
        //https://api.themoviedb.org/3/account/{{account_id}}/watchlist/tv

        given()
                .spec(reqSpec)

                .when()
                .get("account/" + accountId + "/watchlist/tv")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }


}
