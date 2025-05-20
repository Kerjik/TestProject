package ApiCS;

import API.Auth;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;


public class BaseApi {
    public String getToken(){
    Auth auth = new Auth("KcLMmxkJMjBD1","test-admin@mail.com");

        return given().contentType(ContentType.JSON).body(auth).post("https://auth.dev-cinescope.krisqa.ru/login")
                .then().extract().jsonPath().getString("accessToken");
    }
}
