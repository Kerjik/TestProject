package API;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.Comparator;
import java.util.List;

public class APITest {
    @Test
    public void qwe(){
        given().get("https://fakestoreapi.com/users")
                .then()
                .log()
                .all()
                .statusCode(200);
    }
    @Test
    public void getSingle(){
        int user = 2;
        given().pathParam("user", user)
                .get("https://fakestoreapi.com/users/{user}")
                .then().log().all()
                .body("id", equalTo(user))
                .body("address.zipcode", matchesPattern("\\d{5}-\\d{4}"));
    }
    @Test
    public void getAllUsersWithLimit(){
        int limiSize = 3;
        given().queryParam("limit", limiSize)
                .get("https://fakestoreapi.com/users")
                .then().log().all()
                .body("", hasSize(limiSize));
    }
    @Test
    public void qwer(){
        String sortType = "desc";
        Response sortedResponse = given().queryParam("sort", sortType)
                .get("https://fakestoreapi.com/users")
                .then().log().all()
                .extract().response();
        Response nonSortedResponse = given()
                .get("https://fakestoreapi.com/users")
                .then().log().all().extract().response();
        List<Integer> sorted = sortedResponse.jsonPath().getList("id");
        List<Integer> nonSorted = nonSortedResponse.jsonPath().getList("id");

        List<Integer> sortedByCode = nonSorted.stream()
                .sorted(Comparator.reverseOrder())
                .toList();

        Assertions.assertNotEquals(sorted, nonSorted);
        Assertions.assertEquals(sorted, sortedByCode);
    }
    @Test
    public void ewq(){

    }}
