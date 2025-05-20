package Conditions;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StatusCodeCondition implements Condition{

    private final int statusCode;

    @Override
    public String toString() {
        return "StatusCode " + statusCode;
    }

    @Override
    public BodyFieldCondition check(Response response) {
        response.then().assertThat().statusCode(statusCode);
        return null;
    }
}
