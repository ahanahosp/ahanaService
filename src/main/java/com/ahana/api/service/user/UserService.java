package com.ahana.api.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.ahana.api.domain.user.Roles;
import com.ahana.api.domain.user.UserProfile;
import com.ahana.api.system.security.exception.AhanaBusinessException;

public interface UserService {

	String loadUserPage(String type,HttpServletRequest request, HttpServletResponse response)throws AhanaBusinessException;

	String createUser(UserProfile userProfile, HttpServletRequest request)throws AhanaBusinessException;

	String loadRolePage(HttpServletRequest request,HttpServletResponse response)throws AhanaBusinessException;

	String getRoleByOid(String roleOid, HttpServletRequest request)throws AhanaBusinessException;

	String getActiveRoles(HttpServletRequest request) throws AhanaBusinessException;

	String editUser(UserProfile userVO, HttpServletRequest request) throws AhanaBusinessException;

	String uploadUserProfilePicture(MultipartFile profilePicture,HttpServletRequest request);

	void downloadPhoto(String userOid,HttpServletRequest request,HttpServletResponse response);

	String populateState(String countryId, HttpServletRequest request,HttpServletResponse response) throws AhanaBusinessException;

	String createRole(Roles roles, BindingResult errorResult)throws AhanaBusinessException;

}
