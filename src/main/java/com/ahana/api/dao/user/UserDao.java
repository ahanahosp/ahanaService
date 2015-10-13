package com.ahana.api.dao.user;


import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ahana.api.domain.user.RoleRights;
import com.ahana.api.domain.user.Roles;
import com.ahana.api.domain.user.UserProfile;
import com.ahana.api.domain.user.UserRole;

public interface UserDao extends UserDetailsService {

    UserDetails loadUserByUsername(String userId);

    UserProfile saveUser(UserProfile userVO);
    
    UserRole createUserRole(UserRole userRoleVO);
    
    Roles createRole(Roles roles);

	String getOldPassword(String oid);

	Roles getRoleByOid(String roleOid);

	List<Map<String, String>> getActiveRoles();

	UserProfile getUserProfileByUserOid(String userOid);

	UserProfile updateUser(UserProfile userProfile);

	void saveRoleRights(List<RoleRights> roleRights);

	List<Map<String, String>> getUser(int intex, int noOfRecords);

} 
