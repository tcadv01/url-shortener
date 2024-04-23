package cl.tcadv.urlshortener.repository;

import cl.tcadv.urlshortener.model.UrlMetric;

public interface UrlMetricRepository {
	public int saveUrlMetric(UrlMetric url);
}
