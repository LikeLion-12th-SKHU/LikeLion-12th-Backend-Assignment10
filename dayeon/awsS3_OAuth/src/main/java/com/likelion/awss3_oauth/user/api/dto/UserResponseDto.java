package com.likelion.awss3_oauth.user.api.dto;

public record UserResponseDto(
        Long id,
        String name,
        String image,
        String content
) {
    public static UserResponseDto of(Long id, String name, String image, String content) {
        return new UserResponseDto(id, name, image, content);
    }
}

