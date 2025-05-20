package responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmCreatedResponse{

	@JsonProperty("genreId")
	private int genreId;

	@JsonProperty("createdAt")
	private String createdAt;

	@JsonProperty("price")
	private int price;

	@JsonProperty("imageUrl")
	private String imageUrl;

	@JsonProperty("name")
	private String name;

	@JsonProperty("genre")
	private Genre genre;

	@JsonProperty("rating")
	private int rating;

	@JsonProperty("description")
	private String description;

	@JsonProperty("location")
	private String location;

	@JsonProperty("id")
	private int id;

	@JsonProperty("published")
	private boolean published;

	}