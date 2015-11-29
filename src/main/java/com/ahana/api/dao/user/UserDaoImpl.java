package com.ahana.api.dao.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.domain.user.RoleRights;
import com.ahana.commons.system.dao.common.AhanaDaoSupport;
import com.ahana.commons.system.domain.user.Login;
import com.ahana.commons.system.domain.user.Roles;
import com.ahana.commons.system.domain.user.UserProfile;
import com.ahana.commons.system.domain.user.UserProfileView;
import com.ahana.commons.system.domain.user.UserRole;
import com.ahana.commons.system.security.error.CommonErrorConstants;
import com.ahana.commons.system.security.util.Constants;

@Transactional(readOnly = false)
public class UserDaoImpl extends AhanaDaoSupport implements UserDao {

	private static Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(final String userId)throws UsernameNotFoundException, DataAccessException {
		List<UserProfileView> users = null;
		UserProfileView userProfileView = null;
		try {
			if(logger.isDebugEnabled()){
				logger.debug("Authentication started for the : "+userId);
			}
			users = findByNamedQuery("getUserProfileViewByUserId", "userId", userId);
			if(CollectionUtils.isEmpty(users)) {
				logger.error("User not found for {}"+userId);
				throw new UsernameNotFoundException(CommonErrorConstants.USERNAME_OR_PASSWORD_IS_INVALID);
			}
			userProfileView = (UserProfileView) users.get(0);
			Date today = new Date();
			// Check if password has expired
			if (userProfileView.getPasswordExpDate().before(today)) {
				userProfileView.setCredentialsNonExpired(false);
			}
			//Check Account Enabled
			if(StringUtils.isNotBlank(userProfileView.getUserStatus()) 
					&& userProfileView.getUserStatus().equalsIgnoreCase("ACT")
					&& StringUtils.isNotBlank(userProfileView.getLoginStatus()) 
					&& userProfileView.getLoginStatus().equalsIgnoreCase("ACT")){
				userProfileView.setEnabled(true);
			}
			if (CollectionUtils.isEmpty(userProfileView.getAuthorities())) {
				logger.error("Authorities not found ");
				throw new UsernameNotFoundException("Authorities not found");
			}
			return userProfileView;
		} catch (Throwable e) {
			logger.error("Authentication failled for user : "+userId + " Error is : "+e.getMessage());
			throw new UsernameNotFoundException(CommonErrorConstants.USERNAME_OR_PASSWORD_IS_INVALID);
		}finally{
			userProfileView=null;
			users=null;
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public UserProfile saveUser(UserProfile userProfile) {
		saveOrUpdate(userProfile);
		return userProfile;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Roles createRole(Roles roles) {
		saveOrUpdate(roles);
		return roles;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createUserRole(UserRole userRoleVO) {
		saveOrUpdate(userRoleVO);
	}

	@SuppressWarnings("unchecked")
	public String getOldPassword(String userOid) {
		List<String> userVOs = findByNamedQuery("getOldPassword", "userOid", userOid.toString());
		if(CollectionUtils.isNotEmpty(userVOs)){
			return userVOs.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Roles getRoleByOid(String roleOid) {
		List<Roles> roles = findByNamedQuery("getRoleByOid", "roleOid", roleOid);
		if(CollectionUtils.isNotEmpty(roles)){
			return roles.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getActiveRoles() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select oid as oid,role_name as name,status as status from roles";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("name")
					.addScalar("status")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public UserProfile getUserProfileByUserOid(String userOid) {
		List<UserProfile> userVOs = new ArrayList<UserProfile>();
		userVOs = findByNamedQuery(UserProfile.GET_USER_PROFILE_BY_USER_OID, "userOid", userOid);
		if(CollectionUtils.isEmpty(userVOs)){
			return new UserProfile();
		}else{
			return userVOs.get(0);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public UserProfile updateUser(UserProfile userProfile) {
		saveOrUpdate(userProfile);
		return userProfile;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveRoleRights(RoleRights roleRights) {
		saveOrUpdate(roleRights);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getUser(int intex, int noOfRecords) {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select u.oid as oid,u.salutation as salutation,u.fullName as fullName,u.designation as designation,u.userStatus as userStatus,"
					+ "u.emailId as emailId,u.mobileNo as mobileNo,u.speciality as speciality,u.careProvider as careProvider from user_view u";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("salutation")
					.addScalar("fullName")
					.addScalar("designation")
					.addScalar("userStatus")
					.addScalar("emailId")
					.addScalar("mobileNo")
					.addScalar("speciality")
					.addScalar("careProvider")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setFirstResult(intex*noOfRecords).setMaxResults(noOfRecords);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@Override
	public void deleteRole(String roleOid) {
		Query q = getSessionFactory().getCurrentSession().createQuery("update Roles set status='INACT' where oid ='"+roleOid+"'");
		q.executeUpdate();		
	}

	@Override
	public void deleteUser(String userOid) {
		Query q = getSessionFactory().getCurrentSession().createQuery("update UserProfile set userStatus='INACT' where oid ='"+userOid+"'");
		q.executeUpdate();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllUserOidAndName() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select u.oid as value,l.user_id as label from user_profile u join login l on u.oid=l.user_oid "
					+ "where u.user_status='"+Constants.ACT+"' and l.status='"+Constants.ACT+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("value")
					.addScalar("label")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getSavedRolesByUserOid(String userOid) {
		Query sqlQuery=null;
		List<String> list=null;
		String query=null;
		try{
			query="select role_oid as roleOid from user_roles where user_oid='"+userOid+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getSavedRightsByRoleOid(String roleOid) {
		Query sqlQuery=null;
		List<String> list=null;
		String query=null;
		try{
			query="select module_oid as moduleOid from role_rights where role_oid='"+roleOid+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteRoleRights(String roleOid) {
		Query q = getSessionFactory().getCurrentSession().createQuery("delete RoleRights where roleOid ='"+roleOid+"'");
		q.executeUpdate();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteUserRole(String userOid) {
		Query q = getSessionFactory().getCurrentSession().createQuery("delete UserRole where userOid ='"+userOid+"'");
		q.executeUpdate();
	}

	@Override
	public Login saveLogin(Login login) {
		saveOrUpdate(login);
		return login;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Login getLoginByOid(String loginOid) {
		List<Login> logins = findByNamedQuery("getLoginByUserOid", "userOid", loginOid);
		if(CollectionUtils.isNotEmpty(logins)){
			return logins.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllLogin(int index, int noOfRecords) {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select l.user_id as userId,l.activation_date as activationDate,l.password_exp_date as inactivationDate,l.status as status,l.password as password,"
					+ "up.first_name as firstName,up.last_name as lastName,l.user_oid as userOid from login l join user_profile up on l.user_oid=up.oid";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("userId")
					.addScalar("activationDate")
					.addScalar("inactivationDate")
					.addScalar("status")
					.addScalar("firstName")
					.addScalar("lastName")
					.addScalar("userOid")
					.addScalar("password")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setFirstResult(index*noOfRecords).setMaxResults(noOfRecords);;
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@Override
	public void deleteLogin(String loginOid) {
		Query q = getSessionFactory().getCurrentSession().createQuery("update Login set status='INACT' where user_oid ='"+loginOid+"'");
		q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getActiveUsers() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select oid as value,CONCAT(first_name,\" \",last_name) label from user_profile where user_status='"+Constants.ACT+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("value")
					.addScalar("label")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}
}