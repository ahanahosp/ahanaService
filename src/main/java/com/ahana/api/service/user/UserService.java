package com.ahana.api.service.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ahana.api.domain.user.RoleRights;
import com.ahana.commons.system.domain.user.Login;
import com.ahana.commons.system.domain.user.Roles;
import com.ahana.commons.system.domain.user.UserProfile;
import com.ahana.commons.system.domain.user.UserRole;
import com.ahana.commons.system.security.exception.AhanaBusinessException;

public interface UserService {


	Map<String, Object> updateUser(UserProfile user) throws AhanaBusinessException;

	Map<String, Object> createUserRole(UserRole userRole) throws AhanaBusinessException;

	Map<String, Object> createUser(UserProfile userProfile) throws AhanaBusinessException;

	Map<String, Object> getRoleByOid(String roleOid) throws AhanaBusinessException;

	Map<String, Object> createRole(Roles roles) throws AhanaBusinessException;

	Map<String, Object> getActiveRoles(HttpServletRequest request) throws AhanaBusinessException;

	Map<String, Object> getUserByOid(String userOid) throws AhanaBusinessException;

	Map<String, Object> getUser(int intex,int noOfRecords) throws AhanaBusinessException;

	Map<String, Object> deleteRole(String roleOid) throws AhanaBusinessException;

	Map<String, Object> deleteUser(String userOid) throws AhanaBusinessException;

	Map<String, Object> getAllUserOidAndName() throws AhanaBusinessException;

	Map<String, Object> getSavedRolesByUserOid(String userOid) throws AhanaBusinessException;

	Map<String, Object> getSavedRightsByRoleOid(String roleOid) throws AhanaBusinessException;

	Map<String, Object> saveRoleRights(RoleRights roleRights) throws AhanaBusinessException;

	Map<String, Object> getLoginByOid(String loginOid) throws AhanaBusinessException;

	Map<String, Object> deleteLogin(String loginOid) throws AhanaBusinessException;

	Map<String, Object> getAllLogin(int index, int noOfRecords) throws AhanaBusinessException;

	Map<String, Object> getActiveUsers() throws AhanaBusinessException;

	Map<String, Object> createLogin(Login login) throws AhanaBusinessException;

}