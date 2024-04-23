package cl.tcadv.urlshortener.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cl.tcadv.urlshortener.model.UrlDetail;

@Repository
public class UrlShortenerRepositoryImpl implements UrlShortenerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int saveUrl(UrlDetail url) {
		int affectedRows = jdbcTemplate.update("insert into url_detail (id, url, enabled) values(?, ?, ?)", url.getId(),
				url.getUrl(), url.isEnabled());
		return affectedRows;
	}

	@Override
	public UrlDetail getUrl(long id) {
		try {
			UrlDetail urlDetail = jdbcTemplate.queryForObject("select id, url, enabled from url_detail where id = ?",
					new UrlDetailRowMapper(), id);
			return urlDetail;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public int updateUrl(UrlDetail url) {
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("update url_detail set ");
		if (url.getUrl() != null) {
			params.add(url.getUrl());
			query.append("url = ?,");
		}
		if (url.isEnabled() != null) {
			query.append("enabled = ?,");
			params.add(url.isEnabled());
		}
		query.deleteCharAt(query.length() - 1);
		query.append(" where id = ?");
		params.add(url.getId());
		int affectedRows = jdbcTemplate.update(query.toString(), params.toArray());
		return affectedRows;
	}

}
