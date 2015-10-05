package com.ahana.api.dao.common.fileupload;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.dao.common.AhanaDaoSupport;
import com.ahana.api.domain.user.DocFile;

@Transactional(readOnly=false)
public class FileUploadDaoImpl extends AhanaDaoSupport implements FileUploadDao {

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void uploadFile(DocFile docFile) {
		getHibernateTemplate().saveOrUpdate(docFile);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DocFile getUserPhoto(String userOid) {
		List<DocFile> docFiles = findByNamedQuery("getUserPhotoByUserOid", "userOid", userOid);
		if(CollectionUtils.isNotEmpty(docFiles)){
			return docFiles.get(0);
		}
		return null;
	}
	
}