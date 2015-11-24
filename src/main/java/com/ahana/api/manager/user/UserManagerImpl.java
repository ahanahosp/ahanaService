package com.ahana.api.manager.user;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.dao.user.UserDao;
import com.ahana.api.domain.user.RoleRights;
import com.ahana.api.manager.common.CommonManager;
import com.ahana.commons.system.domain.user.Roles;
import com.ahana.commons.system.domain.user.UserProfile;
import com.ahana.commons.system.domain.user.UserRole;
import com.ahana.commons.system.security.error.CommonErrorConstants;
import com.ahana.commons.system.security.exception.AhanaBusinessException;
import com.ahana.commons.system.security.util.Constants;

@Transactional(propagation = Propagation.REQUIRED)
public class UserManagerImpl implements UserManager {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CommonManager commonManager;
	
	@Override
	public UserProfile createUser(UserProfile userProfile) throws AhanaBusinessException {
		if(userProfile==null){
			throw new AhanaBusinessException(CommonErrorConstants.USER_NOT_FOUND);
		}
		if(StringUtils.isBlank(userProfile.getPassword())){
			userProfile.setPassword(UserProfile.DEFAULT_PASSWORD);
			Md5PasswordEncoder ms = new Md5PasswordEncoder();
			userProfile.setPassword(ms.encodePassword(userProfile.getPassword(), null));
		}
		userDao.saveUser(userProfile);
		return userProfile;
	}

	@Override
	public Roles createRole(Roles roles) throws AhanaBusinessException {
		if(roles==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		userDao.createRole(roles);
		return roles;
	}

	@Override
	public Roles getRoleByOid(String roleOid) throws AhanaBusinessException {
		Roles roles=userDao.getRoleByOid(roleOid);
		if(roles==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return roles;
	}

	@Override
	public List<Map<String, String>> getActiveRoles() throws AhanaBusinessException {
		List<Map<String, String>> roles=userDao.getActiveRoles();
		if(CollectionUtils.isEmpty(roles)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return roles;
	}

	@Override
	public UserProfile getUserProfileByUserOid(String userOid) throws AhanaBusinessException {
		UserProfile userProfile=userDao.getUserProfileByUserOid(userOid);
		if(userProfile==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return userProfile;
	}

	@Override
	public UserProfile updateUser(UserProfile userProfile) throws AhanaBusinessException {
		if(userProfile==null){
			throw new AhanaBusinessException(CommonErrorConstants.USER_NOT_FOUND);
		}
		userProfile=userDao.updateUser(userProfile);
		return userProfile;
	}

	@Override
	public UserRole createUserRole(UserRole userRole) throws AhanaBusinessException {
		if(userRole==null || CollectionUtils.isEmpty(userRole.getRoleOids())){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		for(String roleOid:userRole.getRoleOids()){
			UserRole userRole2=new UserRole();
			userRole2.setRoleOid(roleOid);
			userRole2.setUserOid(userRole.getUserOid());
			userDao.createUserRole(userRole2);
		}
		return userRole;
	}

	@Override
	public RoleRights saveRoleRights(RoleRights roleRight) throws AhanaBusinessException {
		if(roleRight==null){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		if(CollectionUtils.isNotEmpty(roleRight.getModuleOids())){
			for(String moduleOid:roleRight.getModuleOids()){
				RoleRights roleRights2=new RoleRights();
				roleRights2.setModuleOid(moduleOid);
				roleRights2.setOrganizationOid(roleRight.getOrganizationOid());
				roleRights2.setRoleOid(roleRight.getRoleOid());
				userDao.saveRoleRights(roleRights2);
			}
		}
		return roleRight;
		
	}

	@Override
	public List<Map<String, String>> getUser(int intex, int noOfRecords) throws AhanaBusinessException {
		List<Map<String, String>> users=userDao.getUser(intex,noOfRecords);
		if(CollectionUtils.isEmpty(users)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return users;
	}

	@Override
	public void deleteRole(String roleOid) {
		userDao.deleteRole(roleOid);
	}

	@Override
	public void deleteUser(String userOid) {
		userDao.deleteUser(userOid);
	}

	@Override
	public List<Map<String, String>> getAllUserOidAndName() throws AhanaBusinessException {
		List<Map<String, String>> users=userDao.getAllUserOidAndName();
		if(CollectionUtils.isEmpty(users)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		return users;
	}

	@Override
	public List<Map<String, String>> getSavedRolesByUserOid(String userOid) throws AhanaBusinessException {
		List<String> userRoles=userDao.getSavedRolesByUserOid(userOid);
		if(CollectionUtils.isEmpty(userRoles)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		List<Map<String, String>> roles=userDao.getActiveRoles();
		if(CollectionUtils.isNotEmpty(roles)){
			for(Map<String, String> role:roles){
				role.put(Constants.STATUS.toLowerCase(),null);
				if(userRoles.contains(role.get(Constants.OID))){
					role.put(Constants.STATUS.toLowerCase(),Constants.ACT);
				}
			}
		}
		return roles;
	}

	@Override
	public List<Map<String, String>> getSavedRightsByRoleOid(String roleOid) throws AhanaBusinessException {
		List<String> roleRights=userDao.getSavedRightsByRoleOid(roleOid);
		if(CollectionUtils.isEmpty(roleRights)){
			throw new AhanaBusinessException(CommonErrorConstants.NO_RECORDS_FOUND);
		}
		List<Map<String, String>> modules =commonManager.getAllOrganizationModule();
		if(CollectionUtils.isNotEmpty(modules)){
			for(Map<String, String> mods:modules){
				if(roleRights.contains(mods.get(Constants.OID))){
					mods.put(Constants.STATUS.toLowerCase(),Constants.ACT);
				}
			}
		}
		return modules;
	}	
}