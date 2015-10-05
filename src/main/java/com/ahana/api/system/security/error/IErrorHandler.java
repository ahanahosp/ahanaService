package com.ahana.api.system.security.error;

public interface IErrorHandler {

	AhanaResponse handleError(ErrorContext errorContext);
}
