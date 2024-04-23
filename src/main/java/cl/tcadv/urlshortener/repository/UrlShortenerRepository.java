package cl.tcadv.urlshortener.repository;

import cl.tcadv.urlshortener.model.UrlDetail;
import cl.tcadv.urlshortener.model.UrlMetric;

public interface UrlShortenerRepository {
	public int saveUrl(UrlDetail url);

	public UrlDetail getUrl(long id);

	public int updateUrl(UrlDetail url);
}
