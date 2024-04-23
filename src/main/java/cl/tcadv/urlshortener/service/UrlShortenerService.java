package cl.tcadv.urlshortener.service;

import cl.tcadv.urlshortener.exeption.UrlNotFoundException;
import cl.tcadv.urlshortener.model.UrlDetail;

public interface UrlShortenerService {
	public UrlDetail shortenUrl(String url);

	public UrlDetail updateUrl(UrlDetail urlDetail) throws UrlNotFoundException;

	public UrlDetail expandUrl(long id) throws UrlNotFoundException;

	public UrlDetail getUrl(long id) throws UrlNotFoundException;
}
