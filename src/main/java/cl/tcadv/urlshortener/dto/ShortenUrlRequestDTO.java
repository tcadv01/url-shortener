package cl.tcadv.urlshortener.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ShortenUrlRequestDTO {

	@NotBlank
	@NotNull
	@NotEmpty
	@URL
	private String url;

	public ShortenUrlRequestDTO() {
		super();
	}

	public ShortenUrlRequestDTO(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "ShortenUrlRequestDTO [url=" + url + "]";
	}

}
