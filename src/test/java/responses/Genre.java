package responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Genre{

	@JsonProperty("name")
	private String name;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}