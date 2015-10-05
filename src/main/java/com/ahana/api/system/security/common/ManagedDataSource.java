package com.ahana.api.system.security.common;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.util.StringUtils;

public class ManagedDataSource extends BasicDataSource {

	private static final String DATABASE_MYSQLROOT = "database.mySQLRoot";

	private static final String IP_TO_REPLACE = "databaseIP";

	private static final String NAME_TO_REPLACE = "databaseName";

	private static final String PORT_TO_REPLACE = "databasePort";
	
	private String lookupDBUsernameLabel = "database.user";
	private String lookupDBPasswordLabel = "database.password";
	private String lookupDBNameLabel = "database.name";
	private String lookupDBIpLabel = "database.ip";
	private String lookupDBPortLabel = "database.port";
	
	public final String getMySQLRoot() {
		return SystemProperties.getProperty(DATABASE_MYSQLROOT);
	}

	public final String getDatabaseName() {
		return SystemProperties.getProperty(getLookupDBNameLabel());
	}

	public String getLookupDBUsernameLabel() {
		return lookupDBUsernameLabel;
	}

	public void setLookupDBUsernameLabel(String lookupDBUsernameLabel) {
		this.lookupDBUsernameLabel = lookupDBUsernameLabel;
	}

	public String getLookupDBPasswordLabel() {
		return lookupDBPasswordLabel;
	}

	public void setLookupDBPasswordLabel(String lookupDBPasswordLabel) {
		this.lookupDBPasswordLabel = lookupDBPasswordLabel;
	}

	public String getLookupDBNameLabel() {
		return lookupDBNameLabel;
	}

	public void setLookupDBNameLabel(String lookupDBNameLabel) {
		this.lookupDBNameLabel = lookupDBNameLabel;
	}

	public String getLookupDBIpLabel() {
		return lookupDBIpLabel;
	}

	public void setLookupDBIpLabel(String lookupDBIpLabel) {
		this.lookupDBIpLabel = lookupDBIpLabel;
	}
	
	public String getLookupDBPortLabel() {
		return lookupDBPortLabel;
	}

	public void setLookupDBPortLabel(String lookupDBPortLabel) {
		this.lookupDBPortLabel = lookupDBPortLabel;
	}

	public final Connection getConnection() throws SQLException {
		Connection c = super.getConnection();
		c.setAutoCommit(getDefaultReadOnly());
		return c;
	}

	public final String parsePassword() {
		return SystemProperties.getProperty(getLookupDBPasswordLabel());
	}

	public final String parseUsername() {
		return SystemProperties.getProperty(getLookupDBUsernameLabel());
	}

	public final String getDatabasePort() {
		return SystemProperties.getProperty(getLookupDBPortLabel());
	}

	public final String getDatabaseIP() {
		return SystemProperties.getRuntimeProperty(getLookupDBIpLabel());
	}

	public final String parseUrl(final String urlOld) {
		String url = urlOld;
		url = StringUtils.replace(url, IP_TO_REPLACE, getDatabaseIP());
		url = StringUtils.replace(url, NAME_TO_REPLACE, getDatabaseName());
		url = StringUtils.replace(url, PORT_TO_REPLACE, getDatabasePort());
		return url;
	}

	public final void setPassword(final String arg0) {
		super.setPassword(parsePassword());
	}

	public final void setUsername(final String arg0) {
		super.setUsername(parseUsername());
	}

	public final void setUrl(final String arg0) {
		super.setUrl(parseUrl(arg0));
	}
}