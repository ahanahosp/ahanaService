package com.ahana.api.dao.common.fileupload;

import com.ahana.api.domain.user.DocFile;

public interface FileUploadDao {

	void uploadFile(DocFile docFile);

	DocFile getUserPhoto(String userOid);
}
