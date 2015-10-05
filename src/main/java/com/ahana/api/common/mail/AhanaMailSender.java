package com.ahana.api.common.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.ahana.api.common.CommonUtils;
import com.ahana.api.system.security.common.SystemProperties;


public class AhanaMailSender extends JavaMailSenderImpl {

    private final Log logger = LogFactory.getLog(getClass());

    private static final String SMTP_AUTH_USER = (String) SystemProperties.getRuntimeProperty("mail.auth.userName");
    private static final String SMTP_AUTH_PWD  = (String) SystemProperties.getRuntimeProperty("mail.auth.password");

    public AhanaMailSender() {
        super();
        long currentmillis = System.currentTimeMillis();
        if(logger.isDebugEnabled()){
          logger.debug("-------AppMailSender constructor start -------" + currentmillis);
        }
        Properties javaMailProperties = new Properties();
        String returnPath = SystemProperties.getProperty(SystemProperties.RETURNPATH_EMAIL);
        if (!StringUtils.isBlank(returnPath) && CommonUtils.isValidEmailFormat(returnPath)) {
        	if(logger.isDebugEnabled()){
              logger.debug("setting email return path to " + returnPath);
        	}
            String authNeeded = (String) SystemProperties.getRuntimeProperty("mail.auth.needed");
            if(logger.isDebugEnabled()){
              logger.debug("setting email auth needed:: " + authNeeded);
            }
            if (authNeeded != null && authNeeded.equalsIgnoreCase("TRUE")) {
                String userName = (String) SystemProperties.getRuntimeProperty("mail.auth.userName");
                String password = (String) SystemProperties.getRuntimeProperty("mail.auth.password");
                javaMailProperties.put("mail.smtp.starttls.enable", "true"); 
                javaMailProperties.put("mail.smtp.auth", "true"); //enable authentication
                setUsername(userName);
                setPassword(password);
            }
            javaMailProperties.put("mail.smtp.from", returnPath);
            setJavaMailProperties(javaMailProperties);
            Authenticator authenticator = new SMTPAuthenticator();
            Session session= Session.getInstance(javaMailProperties, authenticator);
            setSession(session);
        } else {
        	if(logger.isDebugEnabled()){
              logger.debug("using default email return path");
        	}
        }
        if(logger.isDebugEnabled()){
          logger.debug("-------AxilMailManagerImpl constructor end -------"+ (System.currentTimeMillis() - currentmillis));
        }
    }

    public void sendEmail(final MailDetail mailDetails) throws MailException {
        long currentmillis = System.currentTimeMillis();
    	MimeMessage mimeMessage = new MimeMessage(getSession());
		if (logger.isDebugEnabled()) {
			logger.debug("---AxilMailManager sendEmail method start----" + currentmillis);
		}
		try {
			String isMailServerAvailable = (String) SystemProperties.getProperty("mailserver.available");
			if (isMailServerAvailable.equalsIgnoreCase("TRUE")) {
				if (logger.isDebugEnabled()) {
					logger.debug("inside sendEmail - toAddress:: "+ mailDetails.getEmailId());
					logger.debug("inside sendEmail - BCC:: "+ SystemProperties.getProperty(SystemProperties.AXILADMIN_EMAIL));
					logger.debug("inside sendEmail - FROM:: "+ SystemProperties.getProperty(SystemProperties.AXILSDASH_SENDUS_REPLYTO_EMAIL));
					logger.debug("inside sendEmail - TO:: "+ SystemProperties.getProperty(SystemProperties.AXILSDASH_SENDUS_REPLYTO_EMAIL));
					logger.debug("inside sendEmail - CC:: "+ SystemProperties.getProperty(SystemProperties.MAIL_CC_TO));
				}
				mimeMessage.setFrom(new InternetAddress(SystemProperties.getProperty(SystemProperties.AXILSDASH_SENDUS_REPLYTO_EMAIL)));
				mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(mailDetails.getEmailId()));
				mimeMessage.addRecipient(RecipientType.BCC, new InternetAddress(SystemProperties.getProperty(SystemProperties.AXILADMIN_EMAIL)));
				if(SystemProperties.getProperty(SystemProperties.MAIL_CC_TO) != null) {
					String[] ccaddress = SystemProperties.getProperty(SystemProperties.MAIL_CC_TO).split(",");
					if(ccaddress.length > 0) {
						InternetAddress[] addresses = new InternetAddress[ccaddress.length];
						for(int i=0;i<ccaddress.length;i++) {
							addresses[i] = new InternetAddress(ccaddress[i]);
						}
	                	mimeMessage.addRecipients(RecipientType.CC, addresses);
					}
                }
				InternetAddress[] replyToAddress = new InternetAddress[1];
				replyToAddress[0] = new InternetAddress(SystemProperties.getProperty(SystemProperties.AXILSDASH_SENDUS_REPLYTO_EMAIL));
				mimeMessage.setReplyTo(replyToAddress);
				mimeMessage.setSubject(mailDetails.getSubject());
				mimeMessage.setContent(mailDetails.getMailBodyContent(),"text/html");
				mimeMessage.addHeader("X-Priority",mailDetails.getPriority());
				send(mimeMessage);
				if (logger.isDebugEnabled()) {
					logger.debug("-----------Mail Sent Successfully---");
				}
			} else {
				logger.error("Mail server not configured");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("---AxilMailManager sendEmail method end----"+ (System.currentTimeMillis() - currentmillis));
			}
		} catch (AddressException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Error:"+ e.getMessage());
			}
			throw new MailPreparationException(e.getMessage());
		} catch (MessagingException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Error:"+ e.getMessage());
			}
			throw new MailPreparationException(e.getMessage());
		}
    }

    public final String getHost() {
        return SystemProperties.getRuntimeProperty(SystemProperties.MAIL_SERVER_IP);
    }

    public final void setHost(final String arg0) {
        super.setHost(SystemProperties.getRuntimeProperty(SystemProperties.MAIL_SERVER_IP));
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
           String username = SMTP_AUTH_USER;
           String password = SMTP_AUTH_PWD;
           return new PasswordAuthentication(username, password);
        }
    }

    public static void main(String args[]) {
    }

}