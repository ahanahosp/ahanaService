package com.ahana.api.dao.user;


import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ahana.api.domain.user.RoleRights;
import com.ahana.commons.system.domain.user.Roles;
import com.ahana.commons.system.domain.user.UserProfile;
import com.ahana.commons.system.domain.user.UserRole;

public interface UserDao extends UserDetailsService {

    UserDetails loadUserByUsername(String userId);

    UserProfile saveUser(UserProfile userVO);
    
    void createUserRole(UserRole userRoles);
    
    Roles createRole(Roles roles);

	String getOldPassword(String oid);

	Roles getRoleByOid(String roleOid);

	List<Map<String, String>> getActiveRoles();

	UserProfile getUserProfileByUserOid(String userOid);

	UserProfile updateUser(UserProfile userProfile);

	void saveRoleRights(RoleRights roleRights);

	List<Map<String, String>> getUser(int intex, int noOfRecords);

	void deleteRole(String roleOid);

	void deleteUser(String userOid);

	List<Map<String, String>> getAllUserOidAndName();

	List<String> getSavedRolesByUserOid(String userOid);

	List<String> getSavedRightsByRoleOid(String roleOid);

} 
