package org.likelion.jangsu.common.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.jangsu.common.dto.BaseResponse;
import org.likelion.jangsu.common.error.ErrorCode;
import org.likelion.jangsu.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Component
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionAdvice {
    // 500 INTERNAL_SERVER_ERROR
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleServerException(final Exception e) {
        log.error("Internal_Sever_Error : {}", e.getMessage(), e);
        return BaseResponse.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    // 기타
<<<<<<< HEAD
    @ExceptionHandler(CustomException.class)
=======
<<<<<<< HEAD
    @ExceptionHandler(CustomException.class)
=======
    @ExceptionHandler(Exception.class)
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
    public BaseResponse handleCustomException(CustomException e) {
        log.error("CustomException : {}", e.getMessage(), e);
        return BaseResponse.error(e.getErrorCode(), e.getMessage());
    }
}
