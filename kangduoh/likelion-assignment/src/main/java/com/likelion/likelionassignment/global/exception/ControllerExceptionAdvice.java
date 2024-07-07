package com.likelion.likelionassignment.global.exception;

import com.likelion.likelionassignment.global.exception.code.SuccessCode;
import com.likelion.likelionassignment.global.template.ApiResponseTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ApiResponseTemplate<String>> handleCustomException(CustomException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ApiResponseTemplate.error(e.getErrorCode(), e.getMessage()));
    }

    public <T> ResponseEntity<ApiResponseTemplate<T>> handleSuccess(SuccessCode successCode, T data) {
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponseTemplate.success(successCode, data));
    }
}
