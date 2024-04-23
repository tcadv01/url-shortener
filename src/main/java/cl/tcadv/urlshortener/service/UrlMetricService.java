package cl.tcadv.urlshortener.service;

public interface UrlMetricService {
	public void saveMetric(long urlId, String userAgent, String ip);
}
