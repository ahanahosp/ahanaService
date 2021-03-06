package com.ahana.api.manager.user;

import java.util.List;
import java.util.Map;

import com.ahana.api.domain.user.RoleRights;
import com.ahana.commons.system.domain.user.Login;
import com.ahana.commons.system.domain.user.Roles;
import com.ahana.commons.system.domain.user.UserProfile;
import com.ahana.commons.system.domain.user.UserRole;
import com.ahana.commons.system.security.exception.AhanaBusinessException;

public interface UserManager {

	UserProfile createUser(UserProfile userVO) throws AhanaBusinessException;

	Roles createRole(Roles roles) throws AhanaBusinessException;

	Roles getRoleByOid(String roleOid) throws AhanaBusinessException;

	List<Map<String, String>> getActiveRoles() throws AhanaBusinessException;

	UserProfile getUserProfileByUserOid(String oid) throws AhanaBusinessException;

	UserProfile updateUser(UserProfile user) throws AhanaBusinessException;

	UserRole createUserRole(UserRole userRole) throws AhanaBusinessException;

	List<Map<String, String>> getUser(int intex, int noOfRecords) throws AhanaBusinessException;

	void deleteRole(String roleOid);

	void deleteUser(String userOid);

	List<Map<String, String>> getAllUserOidAndName() throws AhanaBusinessException;

	List<Map<String, String>> getSavedRolesByUserOid(String userOid) throws AhanaBusinessException;

	List<Map<String, String>> getSavedRightsByRoleOid(String roleOid) throws AhanaBusinessException;

	RoleRights saveRoleRights(RoleRights roleRights) throws AhanaBusinessException;

	Login saveLogin(Login login);

	Login getLoginByOid(String loginOid) throws AhanaBusinessException;

	List<Map<String, String>> getAllLogin(int index, int noOfRecords) throws AhanaBusinessException;

	void deleteLogin(String loginOid);

	List<Map<String, String>> getActiveUsers() throws AhanaBusinessException;

}