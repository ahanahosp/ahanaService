package com.ahana.api.manager.user;

import java.util.List;
import java.util.Map;

import com.ahana.api.domain.user.Roles;
import com.ahana.api.domain.user.UserProfile;
import com.ahana.api.system.security.exception.AhanaBusinessException;

public interface UserManager {

	UserProfile createUser(UserProfile userVO) throws AhanaBusinessException;

	Roles createRole(Roles roles) throws AhanaBusinessException;

	Roles getRoleByOid(String roleOid);

	List<Map<String, String>> getActiveRoles();

	UserProfile getUserProfileByUserOid(String oid);

}
