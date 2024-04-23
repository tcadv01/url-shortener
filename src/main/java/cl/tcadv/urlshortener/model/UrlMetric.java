package cl.tcadv.urlshortener.model;

import java.util.Date;

public class UrlMetric {
	private long id;
	private long urlId;
	private Date executedAt;
	private String userAgent;
	private String ip;

	public UrlMetric(long id, long urlId, Date executedAt, String userAgent, String ip) {
		super();
		this.id = id;
		this.urlId = urlId;
		this.executedAt = executedAt;
		this.userAgent = userAgent;
		this.ip = ip;
	}

	public UrlMetric() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUrlId() {
		return urlId;
	}

	public void setUrlId(long urlId) {
		this.urlId = urlId;
	}

	public Date getExecutedAt() {
		return executedAt;
	}

	public void setExecutedAt(Date executedAt) {
		this.executedAt = executedAt;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "UrlMetric [id=" + id + ", urlId=" + urlId + ", executedAt=" + executedAt + ", userAgent=" + userAgent
				+ ", ip=" + ip + "]";
	}
}
