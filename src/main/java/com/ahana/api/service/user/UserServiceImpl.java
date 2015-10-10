package com.ahana.api.service.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahana.api.common.BaseService;
import com.ahana.api.domain.user.RoleRights;
import com.ahana.api.domain.user.Roles;
import com.ahana.api.domain.user.UserProfile;
import com.ahana.api.domain.user.UserRole;
import com.ahana.api.manager.user.UserManager;
import com.ahana.api.system.security.exception.AhanaBusinessException;

@Controller
@RequestMapping("/services/rest/user")
public class UserServiceImpl extends BaseService implements UserService {
	
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserManager userManager;
	
	@Override
	@RequestMapping(value = "/createUser",method=RequestMethod.POST)
	public @ResponseBody String createUser(@Valid @RequestBody UserProfile userProfile, BindingResult errorResult) throws AhanaBusinessException {
		String jsonResponse=null;
		if (errorResult.hasErrors()) {
			return handleError(errorResult.getAllErrors());
		}
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("createUser----start--->"	+ System.currentTimeMillis());
			}
			userProfile=userManager.createUser(userProfile);
			jsonResponse = handleSuccess("userProfile",userProfile);
			if (logger.isDebugEnabled()) {
				logger.debug("createUser: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("createUser::: Error:::", exc);
		}
		return jsonResponse;
	}
	
	@Override
	@RequestMapping(value = "/createRole",method=RequestMethod.POST)
	public @ResponseBody String createRole(@Valid @RequestBody Roles roles, BindingResult errorResult) throws AhanaBusinessException {
		String jsonResponse=null;
		if(errorResult.hasErrors()){
			return handleError(errorResult.getAllErrors());
		}
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("createRole----start--->"	+ System.currentTimeMillis());
			}
			roles=userManager.createRole(roles);
			jsonResponse = handleSuccess("roles",roles);
			if (logger.isDebugEnabled()) {
				logger.debug("createRole: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("createRole::: Error:::", exc);
		}
		return jsonResponse;
	}

	@Override
	@RequestMapping(value = "/getRoleByOid",method=RequestMethod.GET)
	public @ResponseBody String getRoleByOid(@RequestParam("oid") String roleOid) throws AhanaBusinessException {
		String jsonResponse=null;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("getRoleByOid----start--->"	+ System.currentTimeMillis());
			}
			Roles roles=userManager.getRoleByOid(roleOid);
			jsonResponse = handleSuccess("roles",roles);
			if (logger.isDebugEnabled()) {
				logger.debug("getRoleByOid: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("getRoleByOid::: Error:::", exc);
		}
		return jsonResponse;
	}
	
	@Override
	@RequestMapping(value = "/getActiveRoles",method=RequestMethod.GET)
	public @ResponseBody String getActiveRoles(HttpServletRequest request) throws AhanaBusinessException {
		String jsonResponse=null;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("getActiveRoles----start--->"	+ System.currentTimeMillis());
			}
			List<Map<String,String>> roles=userManager.getActiveRoles();
			jsonResponse=handleSuccess("roleDetails",roles);
			if (logger.isDebugEnabled()) {
				logger.debug("getActiveRoles: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("getActiveRoles::: Error:::", exc);
		}
		return jsonResponse;
	}
	
	@Override
	@RequestMapping(value = "/getUserByOid",method=RequestMethod.GET)
	public @ResponseBody String getUserByOid(@RequestParam("oid") String userOid) throws AhanaBusinessException {
		String jsonResponse=null;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("getUserByOid----start--->"	+ System.currentTimeMillis());
			}
			UserProfile userProfile=userManager.getUserProfileByUserOid(userOid);
			jsonResponse = handleSuccess("userProfile",userProfile);
			if (logger.isDebugEnabled()) {
				logger.debug("getUserByOid: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("getUserByOid::: Error:::", exc);
		}
		return jsonResponse;
	}
	
	@Override
	@RequestMapping(value = "/updateUser",method=RequestMethod.POST)
	public String updateUser(@Valid @RequestBody UserProfile user, BindingResult errorResult) throws AhanaBusinessException {
		String jsonResponse=null;
		if(errorResult.hasErrors()){
			return handleError(errorResult.getAllErrors());
		}
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("editUser----start--->"	+ System.currentTimeMillis());
			}
			UserProfile userProfile = userManager.updateUser(user);
			jsonResponse=handleSuccess("userProfile",userProfile);
			if (logger.isDebugEnabled()) {
				logger.debug("editUser: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("editUser::: Error:::", exc);
		}
		return jsonResponse;
	}
	
	@Override
	@RequestMapping(value = "/createUserRole",method=RequestMethod.POST)
	public String createUserRole(@Valid @RequestBody UserRole userRole, BindingResult errorResult) throws AhanaBusinessException {
		String jsonResponse=null;
		if(errorResult.hasErrors()){
			return handleError(errorResult.getAllErrors());
		}
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("createUserRole----start--->"	+ System.currentTimeMillis());
			}
			UserRole userRoles = userManager.createUserRole(userRole);
			jsonResponse=handleSuccess("userRole",userRoles);
			if (logger.isDebugEnabled()) {
				logger.debug("createUserRole: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("createUserRole::: Error:::", exc);
		}
		return jsonResponse;
	}
	
	@Override
	@RequestMapping(value = "/saveRoleRights",method=RequestMethod.POST)
	public String saveRoleRights(@Valid @RequestBody RoleRights roleRights, BindingResult errorResult) throws AhanaBusinessException {
		String jsonResponse=null;
		if(errorResult.hasErrors()){
			return handleError(errorResult.getAllErrors());
		}
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("saveRoleRights----start--->"	+ System.currentTimeMillis());
			}
			RoleRights roleRights2 = userManager.saveRoleRights(roleRights);
			jsonResponse=handleSuccess("userRole",roleRights2);
			if (logger.isDebugEnabled()) {
				logger.debug("saveRoleRights: Success");
			}
		} catch (Throwable exc) {
			jsonResponse = handleError(exc);
			logger.error("saveRoleRights::: Error:::", exc);
		}
		return jsonResponse;
	}
	
	
}