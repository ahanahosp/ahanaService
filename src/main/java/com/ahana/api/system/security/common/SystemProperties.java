package com.ahana.api.system.security.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class SystemProperties {

	public static final String AXILADMIN_EMAIL = "axilAdmin.email";

	public static final String RETURNPATH_EMAIL = "returnPath.email";

	public static final String AXILSDASH_SENDUS_REPLYTO_EMAIL = "axilsDashSendusReplyTo.email";

	public static final String NO_OF_LOGIN_ATTEMPT = "allow.login.attempt";

	public static final String MAIL_SERVER_IP = "mailserver.ip";

	private static Logger logger = Logger.getLogger(SystemProperties.class);

	private static SystemProperties _singleton_ = new SystemProperties("config/system");

	private static final String OVERRIDE_SUFFIX = "-local";

	private Properties _runtime = new Properties();

	private Properties _basic = new Properties();

	private Properties _secured = new Properties(_basic);

	public static final String MAIL_CC_TO = "mail.cc.to";
	
	private SystemProperties(String name) {
		loadProperty(name, null);
	}

	public static SystemProperties getInstance() {
		if (_singleton_ == null) {
			String name = System.getProperty("system.settings");
			if (name == null) {
				name = "config/system";
			}
			_singleton_ = new SystemProperties(name);
		}
		return _singleton_;
	}

	public static String getProperty(String name) {
		String value = getInstance()._secured.getProperty(name);
		if (value == null) {
			return getInstance()._runtime.getProperty(name);
		} else {
			return value;
		}
	}

	public static int getPropertyAsInt(String name) {
		if(getProperty(name) != null){
			return Integer.parseInt(getProperty(name));
		}
		return 0;
	}

	public static String getProperty(String name, String defaultValue) {
		String value = getInstance()._secured.getProperty(name);
		if (value == null) {
			return getInstance()._runtime.getProperty(name, defaultValue);
		} else {
			return value;
		}
	}

	public static String getRuntimeProperty(String name) {
		String value = getInstance()._runtime.getProperty(name);
		if (value == null) {
			return getInstance()._secured.getProperty(name);
		} else {
			return value;
		}
	}

	public static String getRuntimeProperty(String name, String defaultValue) {
		String value = getInstance()._runtime.getProperty(name);
		if (value == null) {
			return getInstance()._secured.getProperty(name, defaultValue);
		} else {
			return value;
		}
	}

	public static boolean isValidRuntimeProperty(String name) {
		if (StringUtils.isEmpty(getInstance()._runtime.getProperty(name))) {
			return false;
		}
		return true;
	}

	public static Object setRuntimeProperty(String name, String value) {
		if (StringUtils.isEmpty(value)) {
			return getInstance()._runtime.remove(name);
		}
		return getInstance()._runtime.setProperty(name, value);
	}

	public static Object setBasicProperty(String name, String value) {
		if (StringUtils.isEmpty(value)) {
			return getInstance()._basic.remove(name);
		}
		return getInstance()._basic.setProperty(name, value);
	}

	public static Object setSecuredProperty(String name, String value) {
		if (StringUtils.isEmpty(value)) {
			return getInstance()._secured.remove(name);
		}
		return getInstance()._secured.setProperty(name, value);
	}

	private void loadProperty(String filename, String prefix) {
		Properties np = new Properties();
		if (prefix == null) {
			prefix = "";
		} else {
			prefix = (prefix + ".");
		}
		try {
			np.load(getPropertyInputStream(filename));
		} catch (Exception ex) {
			logger.error("Cannot load property file " + filename, ex);
		}
		for (Iterator<Object> i = np.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			String value = np.getProperty(key);
			if (StringUtils.isEmpty(value)) {
				continue;
			}
			if (key.equalsIgnoreCase("include.secured")) {
				loadSecuredProperty(value, prefix);
			} else if (key.startsWith("include.")) {
				key = key.substring("include.".length());
				loadProperty(value, prefix + "key");
			} else {
				_basic.put("prefix" + key, value);
			}
		}
	}

	private void loadSecuredProperty(String filename, String prefix) {
		try {
			readSecured(getPropertyInputStream(filename), _secured);
		} catch (Exception ex) {
			logger.error("Cannot find secured property file " + filename, ex);
		}
	}

	private static void readSecured(InputStream f, Properties p) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(f));
		while (true) {
			String v = br.readLine();
			if (v == null) {
				break;
			}
			if (v.length() == 0) {
				continue; // blank line
			}
			try {
				String decoded = new Encryptor(v).next();
				if (decoded != null) {
					v = decoded;
				}
			} catch (Exception ex) {
				ex.getMessage();
			}
			v = v.trim();
			if (v.startsWith("#")) {
				continue; // comments
			}
			int namel = v.indexOf("=");
			if (namel < 1) {
				continue; // ignore blank value
			}
			if (namel == (v.length() - 1)) {
				continue;
			}
			String name = v.substring(0, namel);
			v = v.substring(namel + 1);
			if (!StringUtils.isEmpty(v)) {
				p.put(name, v);
			}
		}
		br.close();
	}
	
	private static InputStream getPropertyInputStream(String name) {
		if (name.endsWith(".properties")) {
			name = name.substring(0, name.length() - ".properties".length());
		}
		InputStream is = FileLoader.getResourceAsInputStream(name+ OVERRIDE_SUFFIX + ".properties");
		if (is == null) {
			is = FileLoader.getResourceAsInputStream(name + ".properties");
			if (is == null) {
				System.out.println("cannot find file");
			}
		}
		return is;
	}

	public Properties getAllRuntimeProperties(){
		return getInstance()._runtime;
	}

	public Properties getAllBasicProperties(){
		return getInstance()._basic;
	}

	public Properties getAllSecuredProperties(){
		return getInstance()._secured;
	}
}