package API;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FilmRoot{

	@JsonProperty("genreId")
	private int genreId;

	@JsonProperty("price")
	private int price;

	@JsonProperty("imageUrl")
	private String imageUrl;

	@JsonProperty("name")
	private String name;

	@JsonProperty("rating")
	private int rating;

	@JsonProperty("description")
	private String description;

	@JsonProperty("location")
	private String location;

	@JsonProperty("published")
	private boolean published;
}