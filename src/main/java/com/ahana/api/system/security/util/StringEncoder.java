package com.ahana.api.system.security.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class StringEncoder extends Md5PasswordEncoder {

	public static String encode(String password) {
		if (password == null) {
			password = "";
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			return encode(md.digest());
		} catch (NoSuchAlgorithmException e) {
			Logger.getLogger(StringEncoder.class).debug(e, e);
		}
		return null;
	}

	private static String encode(final byte[] binaryData) {
		StringBuffer rv = new StringBuffer();
		for (int i = 0; i < binaryData.length; i++) {
			int left = (binaryData[i] & 240) >>> 4;
			rv.append(encode(left));
			int right = binaryData[i] & 15;
			rv.append(encode(right));
		}
		return rv.toString();
	}

	private static char encode(final int i) {
		if (i < 10) {
			return (char) (i + 48);
		}
		if ((i >= 10) && (i <= 16)) {
			return (char) (i + 87);
		}
		return '?';
	}

	public static String passwordOfToday(final String udid) {
		return encode(udid + new java.sql.Date(System.currentTimeMillis())).substring(24);
	}

	@Override
	public final String encodePassword(final String rawPass, final Object salt) throws DataAccessException {
		return encode(rawPass);
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt)throws DataAccessException {
		String pass1 = encPass + "";
		String pass2 = rawPass;
		if (rawPass != null && rawPass.length() <= 25) {
			pass2 = encode(rawPass);
		}
		return pass1.equals(pass2);
	}
}