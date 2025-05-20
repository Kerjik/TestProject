package Conditions;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matcher;

@RequiredArgsConstructor
public class BodyFieldCondition implements Condition{

    private final String jsonPath;
    private final Matcher matcher;

    @Override
    public String toString() {
        return "BodyField [" + jsonPath  + "] " + matcher;
    }

    @Override
    public BodyFieldCondition check(Response response) {
        response.then().assertThat().body(jsonPath, matcher);
        return this;
    }
}
