package com.example.project.post.api.dto.request;

public record PostCreateReqDto(
        Long id,
        String title,
        String imageFile,

        String content
) {
}
