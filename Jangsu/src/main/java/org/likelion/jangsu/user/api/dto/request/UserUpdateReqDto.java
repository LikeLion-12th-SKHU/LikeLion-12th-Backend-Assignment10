package org.likelion.jangsu.user.api.dto.request;

import jakarta.validation.constraints.*;

public record UserUpdateReqDto(
        @NotBlank(message = "아이디를 필수로 입력하시오.")
        @Pattern(regexp = "^[a-z]$", message = "아이디 형식에 맞지 않습니다.")
        @Size(min = 5, max = 15, message = "아이디는 5자 이상 15자 미만으로 입력하시오.")
        String userId,

        @NotBlank(message = "이름을 필수로 입력하시오.")
        @Size(min = 2, max = 15, message = "이름은 2자 이상 15자 미만으로 입력하시오.")
        String name,

        @Email
        String email
) {
}
