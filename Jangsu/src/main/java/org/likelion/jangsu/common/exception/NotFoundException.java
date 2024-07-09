package org.likelion.jangsu.common.exception;

import org.likelion.jangsu.common.error.ErrorCode;

public class NotFoundException extends CustomException{
    public NotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
