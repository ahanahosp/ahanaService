package com.ahana.api.common;

import java.util.Calendar;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ahana.api.domain.user.UserProfile;
import com.ahana.api.system.security.exception.AhanaBusinessException;
import com.google.gson.Gson;

public class CommonUtils {
	
	public static final String VALID_EMAIL_REGEXP = "[-a-zA-Z0-9!#$%&'*+/=?^_'{|}~]+(?:\\.[-a-zA-Z0-9!#$%&'*+/=?^_'{|}~]+)*@([a-zA-Z0-9](?:[-a-zA-Z0-9]*[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[-a-zA-Z0-9]*[a-zA-Z0-9])?)*(?:\\.[a-zA-Z0-9]{2,}+)|\\[(?:\\d{1,3}(?:\\.\\d{1,3}){3}|IPv6:[0-9A-Fa-f:]{4,39})\\])";

	public static boolean isValidPassword(String password) {
		if(password.length() < 6 || password.length() > 10 ){
			return false;
		}
		Pattern p = Pattern.compile("^[A-Za-z0-9]+$");
		if(!p.matcher(password).matches()){
			 return false;
		}
		return true;
	}
	
	public static boolean isValidEmailFormat(String em) {
        em = StringUtils.trimToEmpty(em);
        if (StringUtils.isBlank(em)) {
            return false;
        }
        return em.matches(VALID_EMAIL_REGEXP);
    }
	
	public static String responseHandler(Authentication authentication){
		if(authentication instanceof UsernamePasswordAuthenticationToken){
			StringBuilder response=new StringBuilder();
			Gson gson=new Gson();
			response.append("{\"Status\":\"Ok\",");
			UserProfile userProfile=(UserProfile) authentication.getPrincipal();
			response.append("\"authentication\":"+gson.toJson(userProfile)+"}");
			return response.toString();
		}else{
			return Constants.ERROR;
		}
	}
	
	public static String getIpAddress(HttpServletRequest request){
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		if(ipAddress==null){
		    ipAddress = request.getRemoteAddr();
		}
		return ipAddress;
	}
	
	public static String getMimeType(String extension) throws AhanaBusinessException{
		String mimeType=null;
		if(StringUtils.isNotBlank(extension)){
			if (extension.contains("pdf")) {
				mimeType = "application/pdf";
			} else if (extension.contains("doc")) {
				mimeType = "application/msword";
			} else if (extension.contains("jpg")) {
				mimeType = "image/jpg";
			} else if (extension.contains("JPG")) {
				mimeType = "image/jpg";
			} else if (extension.contains("jpeg")) {
				mimeType = "image/jpeg";
			} else if (extension.contains("JPEG")) {
				mimeType = "image/jpeg";
			} else if (extension.contains("gif")) {
				mimeType = "image/gif";
			} else if (extension.contains("GIF")) {
				mimeType = "image/gif";
			} else {
				mimeType = "image/png";
			}
		}
		return mimeType;
	}

	public static String generateBranchCode(String branchName) {
		String branchCode = branchName.substring(0, Math.min(branchName.length(), 3));
		branchCode=branchCode+Calendar.DAY_OF_MONTH+Calendar.SECOND;
		return branchCode;
	}

	public static String getInstitutionOid() {
		String institutionOid=null;
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(authentication !=null && authentication.getPrincipal() instanceof UserProfile){
			UserProfile userProfile=(UserProfile) authentication.getPrincipal();
		}
		return institutionOid;
	}

	public static String getInstitutionType() {
		String institutionType=null;
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(authentication !=null && authentication.getPrincipal() instanceof UserProfile){
			UserProfile userProfile=(UserProfile) authentication.getPrincipal();
		}
		return institutionType;
	}
}