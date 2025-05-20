package Conditions;

import io.restassured.response.Response;

public interface Condition {

    BodyFieldCondition check (Response response);
}
