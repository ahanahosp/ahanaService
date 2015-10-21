package com.ahana.api.dao.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.common.Constants;
import com.ahana.api.common.ErrorConstants;
import com.ahana.api.dao.common.AhanaDaoSupport;
import com.ahana.api.domain.user.RoleRights;
import com.ahana.api.domain.user.Roles;
import com.ahana.api.domain.user.UserProfile;
import com.ahana.api.domain.user.UserRole;

@Transactional(readOnly = false)
public class UserDaoImpl extends AhanaDaoSupport implements UserDao {

	private static Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(final String userId)throws UsernameNotFoundException, DataAccessException {
		List<UserProfile> users = null;
		UserProfile userProfile = null;
		List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
		try {
			if(logger.isDebugEnabled()){
				logger.debug("Authentication started for the : "+userId);
			}
			users = findByNamedQuery("getUserProfileByUserId", "userId", userId);
			if(CollectionUtils.isNotEmpty(users)) {
				userProfile = (UserProfile) users.get(0);
				Set<Roles> roles = userProfile.getRoles();
				if (roles != null && roles.size() > 0) {
					for (Object roleObj : roles) {
						Roles roleVO = (Roles) roleObj;
						if (roleVO != null) {
							GrantedAuthority authority = new SimpleGrantedAuthority(roleVO.getRoleName());
							roleList.add(authority);
							userProfile.setRoleOid(roleVO.getOid());
						}
					}
				}
			} else {
				throw new UsernameNotFoundException(ErrorConstants.USERNAME_OR_PASSWORD_IS_INVALID);
			}
			boolean credentialsNonExpired = true;
			Date today = new Date();
			// Check if password has expired
			if (userProfile.getInactivationDate().before(today)) {
				credentialsNonExpired = false;
			}
			boolean enabled = false;
			//Check Account Enabled
			if(StringUtils.isNotBlank(userProfile.getUserStatus()) && userProfile.getUserStatus().equalsIgnoreCase("ACT")){
				enabled=true;
			}
			
			Collection<GrantedAuthority> array = (Collection<GrantedAuthority>) roleList;
			
			//Setting Constructor
			UserDetails userDetails = new UserProfile(userProfile.getOid(),userProfile.getUserId(), userProfile.getPassword(),
					enabled, false, false,credentialsNonExpired, array,userProfile.getRoleOid());

			if (CollectionUtils.isEmpty(userDetails.getAuthorities())) {
				throw new UsernameNotFoundException("Authorities not found");
			}
			return userDetails;
		} catch (Throwable e) {
			throw new UsernameNotFoundException(ErrorConstants.USERNAME_OR_PASSWORD_IS_INVALID);
		}finally{
			userProfile=null;
			users=null;
			roleList=null;
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
		List<String> userVOs = findByNamedQuery(UserProfile.GET_OLD_PASSWORD, "userOid", userOid.toString());
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
			query="select oid as oid,role_name as name,status as status from roles where status='"+Constants.ACT+"'";
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
			query="select up.oid as oid,up.salutation as salutation,up.first_name as firstName,up.last_name as lastName,up.designation as designation,"
					+ "up.user_id as userName,up.password_exp_date as inActivationDate,up.activation_date as activationDate,"
					+ "up.email_id as emailId,up.mobile_no as mobileNo,sp.speciality_name as speciality,up.care_provider as careProvider from user_profile up"
					+ " join speciality_details sp on up.speciality=sp.oid where user_status='"+Constants.ACT+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("salutation")
					.addScalar("firstName")
					.addScalar("lastName")
					.addScalar("designation")
					.addScalar("userName")
					.addScalar("inActivationDate")
					.addScalar("activationDate")
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
		Query q = getSessionFactory().getCurrentSession().createQuery("delete Role where oid ='"+roleOid+"'");
		q.executeUpdate();		
	}

	@Override
	public void deleteUser(String userOid) {
		Query q = getSessionFactory().getCurrentSession().createQuery("delete UserProfile where oid ='"+userOid+"'");
		q.executeUpdate();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllUserOidAndName() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select oid as value,user_id as label from user_profile where user_status='"+Constants.ACT+"'";
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
}