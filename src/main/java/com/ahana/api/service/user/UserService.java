package com.ahana.api.service.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import com.ahana.api.domain.user.Roles;
import com.ahana.api.domain.user.UserProfile;
import com.ahana.api.system.security.exception.AhanaBusinessException;

public interface UserService {

	String createUser(UserProfile userProfile, BindingResult result)throws AhanaBusinessException;

	String getActiveRoles(HttpServletRequest request) throws AhanaBusinessException;

	String createRole(Roles roles, BindingResult errorResult)throws AhanaBusinessException;

	String editUser(UserProfile user, BindingResult errorResult)throws AhanaBusinessException;

	String getRoleByOid(String roleOid) throws AhanaBusinessException;

	String getUserByOid(String userOid) throws AhanaBusinessException;

}
