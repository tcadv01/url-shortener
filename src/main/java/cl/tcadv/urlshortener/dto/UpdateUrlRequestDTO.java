package cl.tcadv.urlshortener.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UpdateUrlRequestDTO {
	@NotBlank
	@NotNull
	@NotEmpty
	@URL
	private String url;
	@NotBlank
	@NotNull
	@NotEmpty
	private boolean enabled;

	public UpdateUrlRequestDTO() {
		super();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "UpdateUrlRequestDTO [url=" + url + ", enabled=" + enabled + "]";
	}
}
