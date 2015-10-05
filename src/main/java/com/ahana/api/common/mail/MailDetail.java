package com.ahana.api.common.mail;

/**
 * Facilitates to hold mail details.
 *
 * @author Selva & Vijay
 */

public class MailDetail {

    private String emailId;

    private String mailBodyContent;

    private String subject;

    /* The X-Priority value ranges from 1 to 5,
     * with 1 being high priority, 3 is normal, and 5 is low.
     * You may set X-Priority to the number alone
     */
    private String priority = "3";


    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMailBodyContent() {
        return mailBodyContent;
    }

    public void setMailBodyContent(String mailBodyContent) {
        this.mailBodyContent = mailBodyContent;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
}

