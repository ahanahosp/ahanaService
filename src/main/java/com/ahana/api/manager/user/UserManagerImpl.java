package com.ahana.api.manager.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.common.CommonUtils;
import com.ahana.api.common.ErrorConstants;
import com.ahana.api.dao.common.fileupload.FileUploadDao;
import com.ahana.api.dao.user.UserDao;
import com.ahana.api.domain.user.DocFile;
import com.ahana.api.domain.user.Roles;
import com.ahana.api.domain.user.UserProfile;
import com.ahana.api.domain.user.UserRole;
import com.ahana.api.system.security.exception.AhanaBusinessException;

@Transactional(propagation = Propagation.REQUIRED)
public class UserManagerImpl implements UserManager {

	private UserDao userDao;
	
	private FileUploadDao fileUploadDao;
	

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserProfile createUser(UserProfile userProfile) throws AhanaBusinessException {
		if(userProfile==null){
			throw new AhanaBusinessException(ErrorConstants.USER_NOT_FOUND);
		}
		
		/*if(!CommonUtils.isValidPassword(userProfile.getPassword()) 
				|| StringUtils.isBlank(userProfile.getPassword())
				|| StringUtils.isBlank(userProfile.getConfirmPassword())){
			throw new AhanaBusinessException(ErrorConstants.INVALID_PASSWORD);
		}*/
		
		if(SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication()!= null){
			//String loggedInUserId = ((UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
			if (StringUtils.isNotBlank(userProfile.getUserId()) && !userProfile.getUserId() .matches(CommonUtils.VALID_EMAIL_REGEXP)) {
					throw new AhanaBusinessException(ErrorConstants.USER_ID_INVALID_FORMAT);
				}
           /* if(StringUtils.isNotBlank(userProfile.getUserId()) && !userProfile.getUserId().toLowerCase().equals(loggedInUserId.toLowerCase())){
            	throw new AhanaBusinessException(CommonErrorConstants.SERVICE_DENIED);
            }*/
		}
		/*if(StringUtils.isNotBlank(userProfile.getOid())){
			String oldPassword=userDao.getOldPassword(userProfile.getOid());
			if(StringUtils.isNotBlank(oldPassword) && oldPassword.equals(userProfile.getPassword())){
				throw new AhanaBusinessException(ErrorConstants.LOGIN_EXISTING_PASSWORD_AND_NEW_PASSWORD_MUST_BE_DIFFERENT);
			}
		}
       
        if(!userProfile.getPassword().equals(userProfile.getConfirmPassword())){
        	throw new AhanaBusinessException(ErrorConstants.LOGIN_NEW_PASSWORD_AND_VERIFY_NEW_PASSWORD_MUST_BE_SAME);
        }*/
		userProfile.setPassword("cc03e747a6afbbcbf8be7668acfebee5");
		Md5PasswordEncoder ms = new Md5PasswordEncoder();
		userProfile.setPassword(ms.encodePassword(userProfile.getPassword(), null));
		userDao.saveUser(userProfile);
		
		//Saving User Role
		UserRole userRole=new UserRole();
		userRole.setRoleOid(userProfile.getRoleOid());
		userRole.setUserOid(userProfile.getOid());
		userDao.createUserRole(userRole);
		return userProfile;
	}

	@Override
	public void uploadUserPhoto(DocFile docFile) {
		fileUploadDao.uploadFile(docFile);
	}

	@Override
	public void createRole(Roles roles) {
		userDao.createRole(roles);
	}

	@Override
	public DocFile getUserPhoto(String userOid) throws AhanaBusinessException {
		DocFile docFile=fileUploadDao.getUserPhoto(userOid);
		if(docFile==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		return docFile;
	}
}