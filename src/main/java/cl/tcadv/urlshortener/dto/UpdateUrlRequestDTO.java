package cl.tcadv.urlshortener.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.AssertTrue;

public class UpdateUrlRequestDTO {

	@URL
	private String url;
	private Boolean enabled;

	public UpdateUrlRequestDTO() {
		super();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@AssertTrue
	public boolean isValid() {
		if (url == null && enabled == null) {
			return false;
		}
		if (url != null && url.trim().equals("")) {
			return false;
		}
		return true;
	}

}
