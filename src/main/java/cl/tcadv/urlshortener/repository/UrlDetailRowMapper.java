package cl.tcadv.urlshortener.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.tcadv.urlshortener.model.UrlDetail;

public class UrlDetailRowMapper implements RowMapper<UrlDetail> {

	@Override
	public UrlDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		UrlDetail urlDetail = new UrlDetail(rs.getLong("id"), rs.getString("url"), rs.getBoolean("enabled"));
		return urlDetail;
	}
}
