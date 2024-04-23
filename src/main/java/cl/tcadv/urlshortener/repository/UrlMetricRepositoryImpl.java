package cl.tcadv.urlshortener.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cl.tcadv.urlshortener.model.UrlMetric;

@Repository
public class UrlMetricRepositoryImpl implements UrlMetricRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int saveUrlMetric(UrlMetric urlMetric) {
		int affectedRows = jdbcTemplate.update(
				"insert into url_metric (id, url_detail_id, executed_at, user_agent, ip) values(?, ?, ?, ?, ?)",
				urlMetric.getId(), urlMetric.getUrlId(), urlMetric.getExecutedAt(), urlMetric.getUserAgent(),
				urlMetric.getIp());
		return affectedRows;
	}

}
