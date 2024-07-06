package com.likelion.likelionassignment.post.api.dto;

public record PostDto(
        Long id,
        String title,
        String content,
        String imageUrl
) {
}
