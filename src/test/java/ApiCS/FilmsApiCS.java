package ApiCS;

import API.FilmRoot;
import assertions.AssertableResponse;

public class FilmsApiCS extends ApiCS{


    public AssertableResponse createFilm(FilmRoot film) {

        return new AssertableResponse(setUp()
                .body(film)
                .post("/movies"));
    }
}
