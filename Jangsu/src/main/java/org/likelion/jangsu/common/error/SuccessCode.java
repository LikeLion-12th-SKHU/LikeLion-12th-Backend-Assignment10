package org.likelion.jangsu.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    // 200 OK
    GET_SUCCESS(HttpStatus.OK, "조회 성공"),
    ARTICLE_UPDATE_SUCCESS(HttpStatus.OK, "게시글 수정 성공"),
    ARTICLE_DELETE_SUCCESS(HttpStatus.OK, "게시글 삭제 완료"),
    USER_UPDATE_SUCCESS(HttpStatus.OK, "사용자 수정 성공"),
    USER_DELETE_SUCCESS(HttpStatus.OK, "사용자 삭제 완료"),

    // 201 CREATED
    ARTICLE_SAVE_SUCCESS(HttpStatus.CREATED, "게시글 등록 완료"),
    USER_SAVE_SUCCESS(HttpStatus.CREATED, "사용자 등록 완료");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
