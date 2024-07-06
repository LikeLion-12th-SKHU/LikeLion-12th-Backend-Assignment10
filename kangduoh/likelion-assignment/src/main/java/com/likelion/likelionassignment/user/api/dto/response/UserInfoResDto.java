package com.likelion.likelionassignment.user.api.dto.response;

import lombok.Builder;

@Builder
public record UserInfoResDto(
        String name,
        String email
) {
}
