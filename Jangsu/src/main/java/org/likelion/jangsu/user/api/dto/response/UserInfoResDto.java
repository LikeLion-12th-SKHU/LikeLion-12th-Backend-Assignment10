package org.likelion.jangsu.user.api.dto.response;

import lombok.Builder;
import org.likelion.jangsu.user.domain.Role;
import org.likelion.jangsu.user.domain.User;

@Builder
public record UserInfoResDto(String userId, String email, String name, Role role) {
    public static UserInfoResDto from(User user){
        return UserInfoResDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}
