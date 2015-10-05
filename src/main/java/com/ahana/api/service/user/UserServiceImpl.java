package com.ahana.api.service.user;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.ahana.api.common.Constants;
import com.ahana.api.common.mail.LookupConstants;
import com.ahana.api.domain.common.AhanaLookupVO;
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
	private LookupManager lookupService;
	
	@Override
	@RequestMapping(value = "/loadUserPage",method=RequestMethod.GET)
	public @ResponseBody String loadUserPage(@RequestParam("type") String type,HttpServletRequest request,HttpServletResponse response) throws AhanaBusinessException {
		String responseJson=null;
		SearchRequest searchRequest=new SearchRequest();
		searchRequest.setType(type);
		//searchRequest=userManager.getUsers(searchRequest);
		responseJson=handleSuccess("userDetails",searchRequest.getResponse());
		return responseJson;
	}
	
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
	@RequestMapping(value = "/loadRole",method=RequestMethod.GET)
	public String loadRole(HttpServletRequest request,HttpServletResponse response) {
		return Constants.DASH_BOARD_HOME;
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
	@RequestMapping(value = "/populateLookupData",method=RequestMethod.GET)
	public @ResponseBody String populateLookupData(@RequestParam("requestFrom") String requestFrom,HttpServletRequest request,HttpServletResponse response) throws AhanaBusinessException {
		String responseJson=null;
		Map<String, List<?>> resultObj=new HashMap<String, List<?>>();
		List<NameValue> countryList= lookupService.getLookupsByCategory(LookupConstants.LOOKUP_COUNTRY);
		resultObj.put("countryDetails", countryList);
		List<NameValue> userTypes= new ArrayList<NameValue>();
		if(StringUtils.isNotBlank(requestFrom)){
			AhanaLookupVO ahanaLookupVO=lookupService.getLookupsByCode(requestFrom);
			NameValue nameValue=new NameValue();
			nameValue.setLabel(ahanaLookupVO.getDescription());
			nameValue.setValue(ahanaLookupVO.getCode());
			userTypes.add(nameValue);
		}else{
			userTypes= lookupService.getLookupsByCategory(LookupConstants.LOOKUP_USER_TYPE);
		}
		resultObj.put("userTypeDetails", userTypes);
		String institutionType=CommonUtils.getInstitutionType();
		List<NameValue> designationDetails=new ArrayList<NameValue>();
		if(StringUtils.isNotBlank(institutionType) && institutionType.equalsIgnoreCase("School")){
			designationDetails=lookupService.getLookupsByCategory(LookupConstants.LOOKUP_DESIG_SCHOOL);
		}else{
			designationDetails=lookupService.getLookupsByCategory(LookupConstants.LOOKUP_DESIG_OTHERS);
		}
		resultObj.put("designationDetails", designationDetails);
		List<NameValue> salutation= lookupService.getLookupsByCategory(LookupConstants.LOOKUP_SALUTATION);
		resultObj.put("salutationDetails", salutation);
		responseJson=handleSuccess("populatedDetails",resultObj);
		return responseJson;
	}
	
	@Override
	@RequestMapping(value = "/populateState",method=RequestMethod.GET)
	public @ResponseBody String populateState(@RequestParam("countryId") String countryId,HttpServletRequest request,HttpServletResponse response) throws AhanaBusinessException {
		String responseJson=null;
		List<NameValue> stateDetails= lookupService.getLookupsByCategory("STATE_"+countryId.toUpperCase());
		responseJson=handleSuccess("stateDetails",stateDetails);
		return responseJson;
	}
	
	@Override
	@RequestMapping(value = "/getUserProfileByOid",method=RequestMethod.GET)
	public @ResponseBody String getUserProfileByOid(@RequestParam("oid") String userOid, HttpServletRequest request) throws AhanaBusinessException {
		String responseJson=null;
		UserProfile userProfile=null;//userManager.getUserProfileByUserOid(userOid);
		if(userProfile!=null){
			Set<Roles> roles = userProfile.getRoles();
			if (roles != null && roles.size() > 0) {
				for (Object roleObj : roles) {
					Roles roleVO = (Roles) roleObj;
					userProfile.setRoleOid(roleVO.getOid());
				}
			}
			userProfile.setRoles(null);
		}
		responseJson=handleSuccess("userProfile",userProfile);
		return responseJson;
	}
}