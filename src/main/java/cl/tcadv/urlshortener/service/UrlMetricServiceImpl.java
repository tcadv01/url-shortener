package cl.tcadv.urlshortener.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.tcadv.urlshortener.model.UrlMetric;
import cl.tcadv.urlshortener.repository.UrlMetricRepository;
import cl.tcadv.urlshortener.util.IdGenerator;

@Service
public class UrlMetricServiceImpl implements UrlMetricService {
	
	private static final Logger logger = LoggerFactory.getLogger(UrlMetricServiceImpl.class);

	@Autowired
	private IdGenerator idGenerator;
	@Autowired
	private UrlMetricRepository urlMetricRepository;

	@Override
	public void saveMetric(long urlId, String userAgent, String ip) {
		try {
			long urlMetricId = this.generateId();
			UrlMetric urlMetric = new UrlMetric(urlMetricId, urlId, new Date(), userAgent, ip);
			this.urlMetricRepository.saveUrlMetric(urlMetric);
		} catch (Exception e) {
			logger.error("there was an error recording metrics", e);
		}
	}

	private long generateId() {
		return this.idGenerator.nextId();
	}

}
