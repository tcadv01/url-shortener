package cl.tcadv.urlshortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.tcadv.urlshortener.exeption.UrlNotFoundException;
import cl.tcadv.urlshortener.model.UrlDetail;
import cl.tcadv.urlshortener.repository.UrlShortenerRepository;
import cl.tcadv.urlshortener.util.IdGenerator;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

	private static final boolean DEFAULT_IS_URL_ENABLED = true;

	@Autowired
	private UrlShortenerRepository urlShortenerRepository;
	@Autowired
	private IdGenerator idGenerator;

	@Override
	public UrlDetail shortenUrl(String url) {
		long id = this.generateId();
		UrlDetail urlDetail = new UrlDetail(id, url, DEFAULT_IS_URL_ENABLED);
		this.urlShortenerRepository.saveUrl(urlDetail);
		return urlDetail;
	}

	@Override
	public UrlDetail getUrl(long id) throws UrlNotFoundException {
		UrlDetail urlDetail = this.urlShortenerRepository.getUrl(id);
		if(urlDetail == null) {
			throw new UrlNotFoundException("url not found");
		}
		return urlDetail;
	}

	@Override
	public UrlDetail updateUrl(UrlDetail urlDetail) throws UrlNotFoundException {
		int updateResult = this.urlShortenerRepository.updateUrl(urlDetail);
		if (updateResult < 1) {
			throw new UrlNotFoundException("url not found");
		}
		return urlDetail;
	}

	private long generateId() {
		return this.idGenerator.nextId();
	}
}
