package com.ahana.api.util;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.ahana.api.domain.user.DocFile;

public class ServiceHelper {

	private static Logger logger = Logger.getLogger(ServiceHelper.class);

	public DocFile populateUserPhoto(MultipartFile userPhoto, String userOid,String mimeType) {
		DocFile dicFile = new DocFile();
		dicFile.setUserOid(userOid);
		dicFile.setFileName(userPhoto.getOriginalFilename());
		dicFile.setMimeType(mimeType);
		try {
			dicFile.setData(userPhoto.getBytes());
		} catch (Exception e) {
			logger.error(e);
		}
		return dicFile;
	}
}
