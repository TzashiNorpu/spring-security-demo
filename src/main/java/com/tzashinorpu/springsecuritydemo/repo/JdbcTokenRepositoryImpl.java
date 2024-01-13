package com.tzashinorpu.springsecuritydemo.repo;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class JdbcTokenRepositoryImpl extends JdbcDaoSupport implements
		PersistentTokenRepository {
	public static final String CREATE_TABLE_SQL = "create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, "
			+ "token varchar(64) not null, last_used timestamp not null)";
	public static final String DEF_TOKEN_BY_SERIES_SQL = "select username,series,token,last_used from persistent_logins where series = ?";
	public static final String DEF_INSERT_TOKEN_SQL = "insert into persistent_logins (username, series, token, last_used) values(?,?,?,?)";
	public static final String DEF_UPDATE_TOKEN_SQL = "update persistent_logins set token = ?, last_used = ? where series = ?";
	public static final String DEF_REMOVE_USER_TOKENS_SQL = "delete from persistent_logins where username = ?";

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		String username = token.getUsername();
		String series = token.getSeries();
		String tokenValue = token.getTokenValue();
		Date date = token.getDate();
		assert this.getJdbcTemplate() != null;
		this.getJdbcTemplate().update(DEF_INSERT_TOKEN_SQL, username, series, tokenValue, date);
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		assert this.getJdbcTemplate() != null;
		this.getJdbcTemplate().update(DEF_UPDATE_TOKEN_SQL, tokenValue, lastUsed, series);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		assert this.getJdbcTemplate() != null;
		return this.getJdbcTemplate().queryForObject(DEF_TOKEN_BY_SERIES_SQL, new RowMapper<PersistentRememberMeToken>() {
			@Override
			public PersistentRememberMeToken mapRow(ResultSet rs, int i) throws SQLException {
				return new PersistentRememberMeToken(
						rs.getString("username"),
						rs.getString("series"),
						rs.getString("token"),
						rs.getDate("last_used")
				);
			}
		}, new Object[]{seriesId});
	}

	@Override
	public void removeUserTokens(String username) {
		assert this.getJdbcTemplate() != null;
		this.getJdbcTemplate().update(DEF_REMOVE_USER_TOKENS_SQL, username);
	}
}
