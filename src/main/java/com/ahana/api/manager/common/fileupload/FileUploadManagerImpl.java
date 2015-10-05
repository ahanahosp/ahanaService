package com.ahana.api.manager.common.fileupload;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.dao.common.fileupload.FileUploadDao;

@Transactional(propagation = Propagation.REQUIRED)
public class FileUploadManagerImpl implements FileUploadManager {

	private FileUploadDao fileUploadDao;

	public FileUploadDao getFileUploadDao() {
		return fileUploadDao;
	}

	public void setFileUploadDao(FileUploadDao fileUploadDao) {
		this.fileUploadDao = fileUploadDao;
	}
	
	
}
