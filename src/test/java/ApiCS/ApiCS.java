package ApiCS;

import com.github.javafaker.Bool;
import config.ProjectConfig;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiCS extends BaseApi{

    protected RequestSpecification setUp(){
        return  RestAssured.
                given().auth().oauth2(getToken()).contentType(ContentType.JSON).filters(getFilters());
    }
    private List<Filter> getFilters(){
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class);
        if (config.logging()){
            return Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter());
        }
        return Collections.emptyList();
    }
}
