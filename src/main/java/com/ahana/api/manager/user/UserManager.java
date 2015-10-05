package com.ahana.api.manager.user;

import com.ahana.api.domain.user.DocFile;
import com.ahana.api.domain.user.Roles;
import com.ahana.api.domain.user.UserProfile;
import com.ahana.api.system.security.exception.AhanaBusinessException;

public interface UserManager {

	UserProfile createUser(UserProfile userVO) throws AhanaBusinessException;

	void uploadUserPhoto(DocFile docFile);

	void createRole(Roles roles);

	DocFile getUserPhoto(String userOid) throws AhanaBusinessException;
	
}
