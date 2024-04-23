package cl.tcadv.urlshortener.model;

public class UrlDetail {
	private long id;
	private String url;
	private boolean enabled;

	public UrlDetail(long id, String url, boolean enabled) {
		super();
		this.id = id;
		this.url = url;
		this.enabled = enabled;
	}

	public UrlDetail() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		return "ShortUrl [id=" + this.id + ", url=" + this.url + ", isEnabled=" + this.enabled + "]";
	}
}
