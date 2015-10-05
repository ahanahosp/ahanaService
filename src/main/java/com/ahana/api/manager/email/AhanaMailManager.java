package com.ahana.api.manager.email;


public interface AhanaMailManager {

    String EMAIL_PASSWORD_RESET = "EMAIL_PASSWORD_RESET";

	String EMAIL_FORGOT_PASSWORD = "EMAIL_FORGOT_PASSWORD";

	String $NEW_PASSWORD$ 	= "$NEW_PASSWORD$";

	String $LOGIN_URL$ 		= "$LOGIN_URL$";

	String $ADMIN_NAME$ 	= "$ADMIN_NAME$";

	String $DASH_BOARD_LOGIN_URL$ 	= "$DASH_BOARD_LOGIN_URL$";

	String $USERNAME$ = "$USERNAME$";

	String $ADMIN_MAILID$ = "$ADMIN_MAILID$";

	String $PASSWORD$ = "$PASSWORD$";

	String $ERROR_MESSAGE$ = "$ERROR_MESSAGE$";
	
    void sendForgotPasswordEmail(String userId, String name,String newPassword, String loginUrl) throws Exception;

    void sendUpdateUserIdEmail(String userId, String name) throws Exception;
}
