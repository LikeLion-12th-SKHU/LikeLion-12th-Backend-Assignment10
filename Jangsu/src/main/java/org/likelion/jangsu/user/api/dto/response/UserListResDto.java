package org.likelion.jangsu.user.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record UserListResDto(List<UserInfoResDto> users) {
    public static UserListResDto from(List<UserInfoResDto> users) {
        return UserListResDto.builder()
                .users(users).build();
    }
}
