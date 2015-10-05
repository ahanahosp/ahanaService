package com.ahana.api.manager.email;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;

import com.ahana.api.common.mail.AhanaMailSender;
import com.ahana.api.system.security.common.FileLoader;

public class MailManager implements InitializingBean{

	private AhanaMailSender appMailSender;

	private Map<String, Object> configMap;

	private Map<String, String> templateContents = new HashMap<String, String>();
	
	public AhanaMailSender getAppMailSender() {
		return appMailSender;
	}

	public void setAppMailSender(AhanaMailSender appMailSender) {
		this.appMailSender = appMailSender;
	}

	public Map<String, Object> getConfigMap() {
		return configMap;
	}

	public void setConfigMap(Map<String, Object> configMap) {
		this.configMap = configMap;
	}

	public Map<String, String> getTemplateContents() {
		return templateContents;
	}

	public void setTemplateContents(Map<String, String> templateContents) {
		this.templateContents = templateContents;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void afterPropertiesSet() throws Exception {
			if (configMap != null && configMap.size() > 0) {
				for (Iterator iter = configMap.keySet().iterator(); iter.hasNext();) {
					String key = (String) iter.next();
					String fileName = (String) configMap.get(key);
					try {
						String fileContent = readFileContent(fileName);
						templateContents.put(key, fileContent);
					} catch (Throwable ex) {
						System.out.println("MailTemplate Not Found for the file : " + fileName);
						System.exit(1);
					}
				}
			} else {
				System.out.println("There is no Email template configured:::");
				System.exit(1);
			}
	}

	private String readFileContent(String fileName) throws IOException {
		InputStream in = null;
		StringBuffer buf = null;
		BufferedReader br = null;
		try {
			in = FileLoader.getResourceAsInputStream(fileName);
			br = new BufferedReader(new InputStreamReader(in));
			buf = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				buf.append(line);
				buf.append("\n");
			}
		} finally {
			br.close();
			in.close();
		}
		return buf.toString();
	}

    @SuppressWarnings("rawtypes")
	public String mergeTemplateWithData(String templateContent,Map mergeDetails) {
		for (Iterator iter = mergeDetails.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			templateContent = StringUtils.replace(templateContent, key,(String) mergeDetails.get(key));
		}
		return templateContent;
	}
}