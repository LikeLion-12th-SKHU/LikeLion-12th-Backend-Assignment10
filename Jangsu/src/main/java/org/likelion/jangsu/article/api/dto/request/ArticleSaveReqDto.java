package org.likelion.jangsu.article.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.likelion.jangsu.user.domain.User;

import java.time.LocalDateTime;

public record ArticleSaveReqDto(
        @NotBlank(message = "게시글 이름를 필수로 입력하시오.")
        @Size(min = 2, max = 30, message = "게시글 이름은 2자 이상 30자 이하로 입력하시오.")
        String articleName,

        @NotBlank(message = "내용을 입력하시오.")
        String content,

        LocalDateTime writeTime,

        String url,

        User user
) {
}
