package com.ahana.api.service.user;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ahana.api.common.BaseService;
import com.ahana.api.common.CommonUtils;
import com.ahana.api.domain.common.NameValue;
import com.ahana.api.domain.common.SearchRequest;
import com.ahana.api.domain.user.DocFile;
import com.ahana.api.domain.user.Roles;
import com.ahana.api.domain.user.UserProfile;
import com.ahana.api.manager.common.lookup.LookupManager;
import com.ahana.api.manager.user.UserManager;
import com.ahana.api.system.security.exception.AhanaBusinessException;
import com.ahana.api.util.ServiceHelper;

@Controller
@RequestMapping("/services/secure/user")
public class UserServiceImpl extends BaseService implements UserService {
	
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private LookupManager lookupCacheManager;
	
	@Override
	@RequestMapping(value = "/createUser",method=RequestMethod.POST)
	public @ResponseBody String createUser(@RequestBody UserProfile userProfile, HttpServletRequest request) throws AhanaBusinessException {
		String responseJson=null;
		userProfile=userManager.createUser(userProfile);
		responseJson=handleSuccess(userProfile.getOid());
		return responseJson;
	}
	
	@Override
	@RequestMapping(value = "/loadRolePage",method=RequestMethod.GET)
	public @ResponseBody String loadRolePage(HttpServletRequest request,HttpServletResponse servletResponse) throws AhanaBusinessException {
		String responseJson=null;
		SearchRequest searchRequest=new SearchRequest();
		//searchRequest=userManager.getRoles(searchRequest);
		responseJson=handleSuccess("roleDetails",searchRequest.getResponse());
		return responseJson;
	}
	
	@Override
	@RequestMapping(value = "/createRole",method=RequestMethod.POST)
	public @ResponseBody String createRole(@Valid Roles roles, BindingResult errorResult) throws AhanaBusinessException {
		String responseJson=null;
		if(null!=errorResult){
			return "";
		}
		userManager.createRole(roles);
		responseJson=handleSuccess(roles);
		return responseJson;
	}

	@Override
	@RequestMapping(value = "/getRoleByOid",method=RequestMethod.GET)
	public @ResponseBody String getRoleByOid(@RequestParam("oid") String roleOid, HttpServletRequest request) throws AhanaBusinessException {
		String responseJson=null;
		Roles roles=null;//userManager.getRoleByOid(roleOid);
		responseJson=handleSuccess("roles",roles);
		return responseJson;
	}
	
	@Override
	@RequestMapping(value = "/getActiveRoles",method=RequestMethod.GET)
	public @ResponseBody String getActiveRoles(HttpServletRequest request) throws AhanaBusinessException {
		String responseJson=null;
		/*List<Map<String,String>> roles=userManager.getActiveRoles();
		responseJson=handleSuccess("roleDetails",roles);*/
		return responseJson;
	}
	
	@Override
	@RequestMapping(value = "/editUser",method=RequestMethod.POST)
	public String editUser(@RequestBody UserProfile user, HttpServletRequest request) throws AhanaBusinessException {
		String responseJson=null;
		if(StringUtils.isNotBlank(user.getOid())){
			/*UserProfile userProfile = userManager.getUserProfileByUserOid(user.getOid());
			if(null!=userProfile){
				userManager.createUser(userProfile);
			}*/
		}
		//user = userManager.getUserProfileByUserId(user.getUserId());
		responseJson=handleSuccess(user);
		return responseJson;
	}

	@Override
	@RequestMapping(value = "/uploadUserPhoto",method=RequestMethod.POST)
	public String uploadUserProfilePicture(@RequestParam("userPhoto") MultipartFile userPhoto, HttpServletRequest request) {
		String responseJson=null;
		String userOid = request.getParameter("userOid");
		String mimeType = request.getParameter("mimeType");
		DocFile docFile=null;
		if(StringUtils.isNotBlank(userOid)){
			ServiceHelper serviceHelper = new ServiceHelper();
			docFile=serviceHelper.populateUserPhoto(userPhoto,userOid,mimeType);
			userManager.uploadUserPhoto(docFile);
		}
		responseJson=handleSuccess(docFile);
		return responseJson;
	}
	
	@Override
	@RequestMapping(value = "/downloadPhoto",method=RequestMethod.GET)
	public void downloadPhoto(@RequestParam("userOid") String userOid,HttpServletRequest request,HttpServletResponse response) {
		String fileName = "NoImage.jpg";
		String mimeType = "image/jpg";;
		OutputStream outPutStream = null;
		DocFile docFile = null;
		try {
			docFile=userManager.getUserPhoto(userOid);
			outPutStream = response.getOutputStream();
			if(StringUtils.isNotBlank(docFile.getOid())){
				if(StringUtils.isNotBlank(docFile.getFileName()) && docFile.getFileName().contains(".")){
					fileName = docFile.getFileName();
				}
				String extension = fileName.substring(fileName.lastIndexOf("."));
				mimeType=CommonUtils.getMimeType(extension);
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				response.setContentType(mimeType);
				response.setCharacterEncoding("UTF-8");
				if(null!=docFile && null!=docFile.getData()){
					outPutStream.write(docFile.getData());
				}else{
					outPutStream.write("NoImage.jpg".getBytes());
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	@Override
	@RequestMapping(value = "/populateState",method=RequestMethod.GET)
	public @ResponseBody String populateState(@RequestParam("countryId") String countryId,HttpServletRequest request,HttpServletResponse response) throws AhanaBusinessException {
		String responseJson=null;
		List<NameValue> stateDetails= lookupCacheManager.getLookupsByCategory("STATE_"+countryId.toUpperCase());
		responseJson=handleSuccess("stateDetails",stateDetails);
		return responseJson;
	}
}