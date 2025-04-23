package tests;

import API.FilmRoot;
import API.Genre;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiTests {
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "https://auth.dev-cinescope.krisqa.ru/";
    }

    @Test
    public void createNewFilm(){

        Map<String,String> auth = new HashMap<>();
        auth.put("email", "test-admin@mail.com");
        auth.put("password", "KcLMmxkJMjBD1");
        String token = "Bearer " + given().contentType(ContentType.JSON).body(auth).post("login")
                .then().statusCode(201).extract().jsonPath().getString("accessToken");
        System.out.println(token);


        FilmRoot bodyRequest = FilmRoot.builder()
                .price(100)
                .imageUrl("https://image.url")
                .location("SPB")
                .genreId(2)
                .description("Описание фильм2а")
                .published(true)
                .name("Название фильма21")
                .build();


        given().log().all().contentType(ContentType.JSON).header("Authorization", token).body(bodyRequest)
                .post("https://api.dev-cinescope.krisqa.ru/movies")
                .then().log().all();
    }
}
