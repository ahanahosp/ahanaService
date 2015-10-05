package com.ahana.api.dao.user;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ahana.api.domain.user.Roles;
import com.ahana.api.domain.user.UserProfile;
import com.ahana.api.domain.user.UserRole;

public interface UserDao extends UserDetailsService {

    UserDetails loadUserByUsername(String userId);

    UserProfile saveUser(UserProfile userVO);
    
    UserRole createUserRole(UserRole userRoleVO);
    
    Roles createRole(Roles roles);

	String getOldPassword(String oid);

} 
