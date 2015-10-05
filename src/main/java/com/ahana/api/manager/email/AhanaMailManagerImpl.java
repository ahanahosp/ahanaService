package com.ahana.api.manager.email;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ahana.api.common.Constants;
import com.ahana.api.common.mail.MailDetail;
import com.ahana.api.system.security.exception.AhanaApplicationException;

public class AhanaMailManagerImpl extends MailManager implements AhanaMailManager {

    private final Log logger = LogFactory.getLog(getClass());

    public final void sendForgotPasswordEmail(final String userId,final String name, final String newPassword,final String loginUrl)throws Exception {
    	if (logger.isDebugEnabled()) {
    			logger.debug("sendForgotPasswordEmail: userId:::"+ userId+"  name:::"+name+"  newPassword:::"+newPassword+"  loginUrl:::"+loginUrl);
		}
    	String mailTemplateName = Constants.FORGOT_PASSWORD_MAIL_TEMPLATE;
    	String templateContent = getTemplateContents().get(mailTemplateName);
		if (templateContent == null || templateContent.length() == 0) {
			throw new AhanaApplicationException("Invalid Mail Template");
		}
		String subject = Constants.PASSWORD_CHANGED;
		final HashMap<String, Object> nameValues = new HashMap<String, Object>();
		if (logger.isDebugEnabled()) {
			logger.debug("New password=" + newPassword);
		}
		nameValues.put($NEW_PASSWORD$, newPassword);
		nameValues.put($LOGIN_URL$, loginUrl);
		nameValues.put($USERNAME$, name);
		String mailBodyContent = mergeTemplateWithData(templateContent,nameValues);
		MailDetail mailDetails = new MailDetail();
		mailDetails.setMailBodyContent(mailBodyContent);
		mailDetails.setEmailId(userId);
		mailDetails.setSubject(subject);
		getAppMailSender().sendEmail(mailDetails);
	}

    /**
     * {@inheritDoc}.
     */
    public final void sendUpdateUserIdEmail(final String newUserId,final String name) throws Exception {
    	if (logger.isDebugEnabled()) {
			logger.debug("sendUpdateUserIdEmail: newUserId:::"+ newUserId);
			logger.debug("sendUpdateUserIdEmail: name:::"+ name);
		}
    	String	mailTemplateName = Constants.UPDATE_USERID_MAIL_TEMPLATE;
    	String templateContent = getTemplateContents().get(mailTemplateName);
		if (templateContent == null || templateContent.length() == 0) {
			throw new AhanaApplicationException("Invalid Mail Template");
		}
		String subject = Constants.USERID_CHANGED;
		final HashMap<String, Object> nameValues = new HashMap<String, Object>();
		nameValues.put($USERNAME$, name);
		String mailBodyContent = mergeTemplateWithData(templateContent,nameValues);
		MailDetail mailDetails = new MailDetail();
		mailDetails.setMailBodyContent(mailBodyContent);
		mailDetails.setEmailId(newUserId);
		mailDetails.setSubject(subject);
		getAppMailSender().sendEmail(mailDetails);
	}

}