package org.likelion.jangsu.user.api.dto.request;

import jakarta.validation.constraints.*;
import org.likelion.jangsu.user.domain.Role;

public record UserSaveReqDto(
        @NotBlank(message = "아이디를 필수로 입력하시오.")
        @Pattern(regexp = "^[a-z]$", message = "아이디 형식에 맞지 않습니다.")
        @Size(min = 5, max = 15, message = "아이디는 5자 이상 15자 미만으로 입력하시오.")
        String userId,

        @Email // 이메일이 없을 수도 있으니 이거 사용했음
        String email,

        @NotBlank(message = "이름을 필수로 입력하시오.")
        @Size(min = 2, max = 15, message = "이름은 2자 이상 15자 미만으로 입력하시오.")
        String name,

        Role role
) {
}
