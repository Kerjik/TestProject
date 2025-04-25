package tests;

import API.Auth;
import API.FilmRoot;
import API.Genre;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
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
    public static void setUp() {
      //  RestAssured.baseURI = "https://api.dev-cinescope.krisqa.ru/";

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    }


    @Test
    public void createNewFilm(){
        Auth auth = new Auth("KcLMmxkJMjBD1","test-admin@mail.com");

        String token = given().contentType(ContentType.JSON).body(auth).post("https://auth.dev-cinescope.krisqa.ru/login")
                .then().statusCode(201).extract().jsonPath().getString("accessToken");
        System.out.println(token);


        FilmRoot bodyRequest = FilmRoot.builder()
                .price(100)
                .imageUrl("https://image.url")
                .location("SPB")
                .genreId(2)
                .description("Описание фильма")
                .published(true)
                .name("Назва11фильма")
                .build();

        given().get("https://api.dev-cinescope.krisqa.ru/genres");

        given().auth().oauth2(token).contentType(ContentType.JSON).body(bodyRequest)
                .post("https://api.dev-cinescope.krisqa.ru/movies").then().statusCode(201);
    }
}
