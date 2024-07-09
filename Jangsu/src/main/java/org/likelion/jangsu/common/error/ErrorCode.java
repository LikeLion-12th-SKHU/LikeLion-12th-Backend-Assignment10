package org.likelion.jangsu.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {
    // 401 UNAUTHORIZED
    USER_NOT_SIGNED_UP(HttpStatus.UNAUTHORIZED,
            "로그인이 되어있지 않습니다.", "UNAUTHORIZED_401"),

    // 404 NOT FOUND
    ARTICLES_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,
            "해당 게시글이 없습니다. ArticleId : ", "NOT_FOUND_404"),
    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,
            "해당 사용자가 없습니다.", "NOT_FOUND_404"),

    // 500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
            "알 수 없는 서버 에러가 발생했습니다.", "INTERNAL_SERVER_ERROR_500");

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    public int getHttpStatus() {
        return httpStatus.value();
    }
}
