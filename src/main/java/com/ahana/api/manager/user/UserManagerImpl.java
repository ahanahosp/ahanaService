package com.ahana.api.manager.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.common.ErrorConstants;
import com.ahana.api.dao.user.UserDao;
import com.ahana.api.domain.user.RoleRights;
import com.ahana.api.domain.user.Roles;
import com.ahana.api.domain.user.UserProfile;
import com.ahana.api.domain.user.UserRole;
import com.ahana.api.system.security.exception.AhanaBusinessException;

@Transactional(propagation = Propagation.REQUIRED)
public class UserManagerImpl implements UserManager {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserProfile createUser(UserProfile userProfile) throws AhanaBusinessException {
		if(userProfile==null){
			throw new AhanaBusinessException(ErrorConstants.USER_NOT_FOUND);
		}
		userProfile.setPassword("cc03e747a6afbbcbf8be7668acfebee5");
		Md5PasswordEncoder ms = new Md5PasswordEncoder();
		userProfile.setPassword(ms.encodePassword(userProfile.getPassword(), null));
		userDao.saveUser(userProfile);
		return userProfile;
	}

	@Override
	public Roles createRole(Roles roles) throws AhanaBusinessException {
		if(roles==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		userDao.createRole(roles);
		return roles;
	}

	@Override
	public Roles getRoleByOid(String roleOid) throws AhanaBusinessException {
		Roles roles=userDao.getRoleByOid(roleOid);
		if(roles==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		return roles;
	}

	@Override
	public List<Map<String, String>> getActiveRoles() throws AhanaBusinessException {
		List<Map<String, String>> roles=userDao.getActiveRoles();
		if(CollectionUtils.isEmpty(roles)){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		return roles;
	}

	@Override
	public UserProfile getUserProfileByUserOid(String userOid) throws AhanaBusinessException {
		UserProfile userProfile=userDao.getUserProfileByUserOid(userOid);
		if(userProfile==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		return userProfile;
	}

	@Override
	public UserProfile updateUser(UserProfile userProfile) throws AhanaBusinessException {
		if(userProfile==null){
			throw new AhanaBusinessException(ErrorConstants.USER_NOT_FOUND);
		}
		userProfile=userDao.updateUser(userProfile);
		return userProfile;
	}

	@Override
	public UserRole createUserRole(UserRole userRole) throws AhanaBusinessException {
		List<UserRole> userRoles=null;
		if(userRole==null || CollectionUtils.isEmpty(userRole.getRoleOids())){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		userRoles=new ArrayList<UserRole>();
		for(String roleOid:userRole.getRoleOids()){
			UserRole userRole2=new UserRole();
			userRole2.setRoleOid(roleOid);
			userRole2.setUserOid(userRole.getUserOid());
			userRoles.add(userRole2);
		}
		userDao.createUserRole(userRoles);
		return userRole;
	}

	@Override
	public RoleRights saveRoleRights(RoleRights roleRight) throws AhanaBusinessException {
		if(roleRight==null){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		if(CollectionUtils.isNotEmpty(roleRight.getModuleOids())){
			List<RoleRights> roleRights=new ArrayList<RoleRights>();
			for(String moduleOid:roleRight.getModuleOids()){
				RoleRights roleRights2=new RoleRights();
				roleRights2.setModuleOid(moduleOid);
				roleRights2.setOrganizationOid(roleRight.getOrganizationOid());
				roleRights2.setRoleOid(roleRight.getRoleOid());
				roleRights.add(roleRights2);
			}
			userDao.saveRoleRights(roleRights);
		}
		return roleRight;
		
	}

	@Override
	public List<Map<String, String>> getUser(int intex, int noOfRecords) throws AhanaBusinessException {
		List<Map<String, String>> users=userDao.getUser(intex,noOfRecords);
		if(CollectionUtils.isEmpty(users)){
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
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
			throw new AhanaBusinessException(ErrorConstants.NO_RECORDS_FOUND);
		}
		return users;
	}	
}