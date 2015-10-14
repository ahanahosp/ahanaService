package com.ahana.api.service.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahana.api.common.BaseService;
import com.ahana.api.domain.user.RoleRights;
import com.ahana.api.domain.user.Roles;
import com.ahana.api.domain.user.UserProfile;
import com.ahana.api.domain.user.UserRole;
import com.ahana.api.manager.user.UserManager;
import com.ahana.api.system.security.exception.AhanaBusinessException;

@RestController
@RequestMapping("/services/rest/secure/user")
public class UserServiceImpl extends BaseService implements UserService {
	
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserManager userManager;
	
	@Override
	@RequestMapping(value = "/createUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> createUser(@Valid @RequestBody UserProfile userProfile) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createUser----start--->"	+ System.currentTimeMillis());
		}
		userProfile=userManager.createUser(userProfile);
		if (logger.isDebugEnabled()) {
			logger.debug("createUser: Success");
		}
		return handleSuccess("userProfile",userProfile);
	}
	
	@Override
	@RequestMapping(value = "/getUser",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getUser(@RequestParam("index") int intex,@RequestParam("noOfRecords") int noOfRecords) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getUser----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String, String>> userDetails=userManager.getUser(intex,noOfRecords);
		if (logger.isDebugEnabled()) {
			logger.debug("getUser: Success");
		}
		return handleSuccess("userProfile",userDetails);
	}
	
	@Override
	@RequestMapping(value = "/deleteUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteUser(@RequestParam("oid") String userOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteUser----start--->"	+ System.currentTimeMillis());
		}
		userManager.deleteUser(userOid);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteUser: Success");
		}
		return handleStatus();
	}
	
	@Override
	@RequestMapping(value = "/createRole",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> createRole(@Valid @RequestBody Roles roles) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createRole----start--->"	+ System.currentTimeMillis());
		}
		roles=userManager.createRole(roles);
		if (logger.isDebugEnabled()) {
			logger.debug("createRole: Success");
		}
		return handleSuccess("roles",roles);
	}

	@Override
	@RequestMapping(value = "/getRoleByOid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getRoleByOid(@RequestParam("oid") String roleOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getRoleByOid----start--->"	+ System.currentTimeMillis());
		}
		Roles roles=userManager.getRoleByOid(roleOid);
		if (logger.isDebugEnabled()) {
			logger.debug("getRoleByOid: Success");
		}
		return handleSuccess("roles",roles);
	}
	
	@Override
	@RequestMapping(value = "/getActiveRoles",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getActiveRoles(HttpServletRequest request) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getActiveRoles----start--->"	+ System.currentTimeMillis());
		}
		List<Map<String,String>> roles=userManager.getActiveRoles();
		if (logger.isDebugEnabled()) {
			logger.debug("getActiveRoles: Success");
		}
		return handleSuccess("roleDetails",roles);
	}
	
	@Override
	@RequestMapping(value = "/deleteRole",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteRole(@RequestParam("oid") String roleOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteRole----start--->"	+ System.currentTimeMillis());
		}
		userManager.deleteRole(roleOid);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteRole: Success");
		}
		return handleStatus();
	}
	
	@Override
	@RequestMapping(value = "/getUserByOid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getUserByOid(@RequestParam("oid") String userOid) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getUserByOid----start--->"	+ System.currentTimeMillis());
		}
		UserProfile userProfile=userManager.getUserProfileByUserOid(userOid);
		if (logger.isDebugEnabled()) {
			logger.debug("getUserByOid: Success");
		}
		return handleSuccess("userProfile",userProfile);
	}
	
	@Override
	@RequestMapping(value = "/updateUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateUser(@Valid @RequestBody UserProfile user) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("editUser----start--->"	+ System.currentTimeMillis());
		}
		UserProfile userProfile = userManager.updateUser(user);
		if (logger.isDebugEnabled()) {
			logger.debug("editUser: Success");
		}
		return handleSuccess("userProfile",userProfile);
	}
	
	@Override
	@RequestMapping(value = "/createUserRole",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> createUserRole(@Valid @RequestBody UserRole userRole) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("createUserRole----start--->"	+ System.currentTimeMillis());
		}
		UserRole userRoles = userManager.createUserRole(userRole);
		if (logger.isDebugEnabled()) {
			logger.debug("createUserRole: Success");
		}
		return handleSuccess("userRole",userRoles);
	}
	
	@Override
	@RequestMapping(value = "/saveRoleRights",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> saveRoleRights(@Valid @RequestBody RoleRights roleRights) throws AhanaBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveRoleRights----start--->"	+ System.currentTimeMillis());
		}
		RoleRights roleRights2 = userManager.saveRoleRights(roleRights);
		if (logger.isDebugEnabled()) {
			logger.debug("saveRoleRights: Success");
		}
		return handleSuccess("roleRights",roleRights2);
	}
	
	
	
}