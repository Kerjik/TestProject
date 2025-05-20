package tests;

import API.Auth;
import API.FilmRoot;
import ApiCS.FilmsApiCS;
import Conditions.Conditions;
import com.github.javafaker.Faker;
import config.ProjectConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import responses.FilmCreatedResponse;


import java.util.*;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class ApiTests {
    private final FilmsApiCS filmsApiCS = new FilmsApiCS();
    private static Faker faker;

    @BeforeAll
    public static void setUp() {
    ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    RestAssured.baseURI = config.baseUrl();
    faker = new Faker(new Locale(config.locale()));
    }

    public Map<String, Object> responseOfCreatedFilmApi(String description){
        RestAssured.baseURI = "https://api.dev-cinescope.krisqa.ru";
        Auth auth = new Auth("KcLMmxkJMjBD1","test-admin@mail.com");

        String token = given().contentType(ContentType.JSON).body(auth).post("https://auth.dev-cinescope.krisqa.ru/login")
                .then().extract().jsonPath().getString("accessToken");

        FilmCreatedResponse response = given().auth().oauth2(token).contentType(ContentType.JSON).log().all().body(createApiFilm(description))
                .post("/movies")
                .as(FilmCreatedResponse.class);
        Map<String, Object> responseCreatedFilm = new HashMap<>();
        responseCreatedFilm.put("id", response.getId());
        responseCreatedFilm.put("description", response.getDescription());
        responseCreatedFilm.put("name", response.getName());
        responseCreatedFilm.put("location", response.getLocation());
        responseCreatedFilm.put("price", response.getPrice());

        return responseCreatedFilm;
    }

    public FilmRoot createApiFilm(String description){
            FilmRoot filmRoot = FilmRoot.builder()
            .price(faker.number().randomDigitNotZero())
                .imageUrl("https://image.url")
                .location("SPB")
                .genreId(2)
                .description(description)
                .published(true)
                .name(faker.name().username())
            .build();

        return filmRoot;
    }


    @Test
    public void createNewFilm(){

        //given
        FilmRoot film = FilmRoot.builder()
                .price(100)
                .imageUrl("https://image.url")
                .location("SPB")
                .genreId(2)
                .description("Описание фильма")
                .published(true)
                .name(faker.name().username())
                .build();
        //expect
        FilmCreatedResponse response = filmsApiCS.createFilm(film).shouldHave(Conditions.statusCode(201))
                .shouldHave(Conditions.bodyField("id", not(empty())))
                .asPojo(FilmCreatedResponse.class);

    }

    public Map<String, Object> responseOfCreatedFilmAp(String description){
        FilmCreatedResponse response = filmsApiCS.createFilm(createApiFilm(description)).shouldHave(Conditions.statusCode(201))
                .shouldHave(Conditions.bodyField("id", not(empty())))
                .asPojo(FilmCreatedResponse.class);
        Map<String, Object> responseCreatedFilm = new HashMap<>();
        responseCreatedFilm.put("id", response.getId());
        responseCreatedFilm.put("description", response.getDescription());
        responseCreatedFilm.put("name", response.getName());
        responseCreatedFilm.put("location", response.getLocation());
        responseCreatedFilm.put("price", response.getPrice());

        return responseCreatedFilm;
    }

}
