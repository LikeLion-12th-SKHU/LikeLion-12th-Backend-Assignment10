package com.likelion.oauth2test.global.exception;

import com.likelion.oauth2test.global.exception.model.CustomException;
import com.likelion.oauth2test.global.exception.model.Error;

public class ForbiddenException extends CustomException {
	public ForbiddenException(Error error, String message){
		super(error,message);
	}


}
