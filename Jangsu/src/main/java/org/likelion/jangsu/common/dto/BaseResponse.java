package org.likelion.jangsu.common.dto;

import lombok.*;
import org.likelion.jangsu.common.error.ErrorCode;
import org.likelion.jangsu.common.error.SuccessCode;

@Getter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResponse<T> {
    private final int code;
    private final String message;
    private T data;

    public static BaseResponse success(SuccessCode success) {
        return new BaseResponse<>(success.getHttpStatusCode(), success.getMessage());
    }

    public static <T> BaseResponse<T> success(SuccessCode success, T data) {
        return new BaseResponse<>(success.getHttpStatusCode(), success.getMessage(), data);
    }

    public static BaseResponse error(ErrorCode error) {
        return new BaseResponse<>(error.getHttpStatus(), error.getMessage());
    }

    public static BaseResponse error(ErrorCode error, String message) {
        return new BaseResponse<>(error.getHttpStatus(), message);
    }
}