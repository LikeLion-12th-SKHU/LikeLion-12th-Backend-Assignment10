package org.likelion.jangsu.common.exception;

import lombok.Getter;
import org.likelion.jangsu.common.error.ErrorCode;

@Getter
public class CustomException extends RuntimeException {
    // 변수 선언
    private final ErrorCode errorCode;

    // 의존관계 형성
    public CustomException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getHttpStatus() {
        return errorCode.getHttpStatus();
    }
}
