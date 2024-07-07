package com.likelion.likelionassignment.post.api.dto.response;

import lombok.Builder;

@Builder
public record PostCreateResDto(
        String title,
        String content,
        String imageUrl
) {
}
