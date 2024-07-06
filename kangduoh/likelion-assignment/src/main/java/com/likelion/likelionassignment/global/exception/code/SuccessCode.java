package com.likelion.likelionassignment.global.exception.code;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    // 200 OK
    LOGIN_USER_SUCCESS(HttpStatus.OK, "로그인 성공"),
    GET_POST_SUCCESS(HttpStatus.OK, "게시글 조회 성공"),

    // 201 Created
    CREATE_POST_SUCCESS(HttpStatus.CREATED, "게시글 작성 성공"),

    // 204 No Content
    DELETE_POST_SUCCESS(HttpStatus.NO_CONTENT, "게시글 삭제 성공");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
