package com.huaman.denunciaservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NO_CONTENT)
public class NoDataServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoDataServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoDataServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NoDataServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoDataServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoDataServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
