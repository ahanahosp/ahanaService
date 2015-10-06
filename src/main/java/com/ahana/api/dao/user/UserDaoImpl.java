package com.ahana.api.dao.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.common.ErrorConstants;
import com.ahana.api.dao.common.AhanaDaoSupport;
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
			if (userProfile.getPasswordExpDate().before(today)) {
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
					enabled, userProfile.isAccountExpired(), userProfile.isAccountLocked(),credentialsNonExpired, array,userProfile.getLanguage(),
					userProfile.getLastLogon(),userProfile.getRoleOid(),userProfile.getUserType());

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
		getHibernateTemplate().saveOrUpdate(userProfile);
		return userProfile;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Roles createRole(Roles roles) {
		getHibernateTemplate().saveOrUpdate(roles);
		return roles;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public UserRole createUserRole(UserRole userRoleVO) {
		getHibernateTemplate().saveOrUpdate(userRoleVO);
		return userRoleVO;
	}

	@SuppressWarnings("unchecked")
	public String getOldPassword(String userOid) {
		List<String> userVOs = findByNamedQuery(UserProfile.GET_OLD_PASSWORD, "userOid", userOid.toString());
		if(CollectionUtils.isNotEmpty(userVOs)){
			return userVOs.get(0);
		}
		return null;
	}
}