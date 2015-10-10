package com.ahana.api.manager.user;

import java.util.List;
import java.util.Map;

import com.ahana.api.domain.user.RoleRights;
import com.ahana.api.domain.user.Roles;
import com.ahana.api.domain.user.UserProfile;
import com.ahana.api.domain.user.UserRole;
import com.ahana.api.system.security.exception.AhanaBusinessException;

public interface UserManager {

	UserProfile createUser(UserProfile userVO) throws AhanaBusinessException;

	Roles createRole(Roles roles) throws AhanaBusinessException;

	Roles getRoleByOid(String roleOid) throws AhanaBusinessException;

	List<Map<String, String>> getActiveRoles() throws AhanaBusinessException;

	UserProfile getUserProfileByUserOid(String oid) throws AhanaBusinessException;

	UserProfile updateUser(UserProfile user) throws AhanaBusinessException;

	UserRole createUserRole(UserRole userRole) throws AhanaBusinessException;

	RoleRights saveRoleRights(RoleRights roleRights) throws AhanaBusinessException;

}
