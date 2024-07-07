package com.example.project.post.api.dto.response;

public record PostResponseDto(
        Long id,
        String title,
        String imageFile,

        String content
) {
    public static PostResponseDto of(Long id, String title, String imageFile, String content) {
        return new PostResponseDto(id, title, imageFile, content);
    }
}
