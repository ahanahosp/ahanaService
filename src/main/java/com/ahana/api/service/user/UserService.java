package com.ahana.api.service.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ahana.api.domain.user.RoleRights;
import com.ahana.api.domain.user.Roles;
import com.ahana.api.domain.user.UserProfile;
import com.ahana.api.domain.user.UserRole;
import com.ahana.api.system.security.exception.AhanaBusinessException;

public interface UserService {


	Map<String, Object> saveRoleRights(RoleRights roleRights) throws AhanaBusinessException;

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

}
