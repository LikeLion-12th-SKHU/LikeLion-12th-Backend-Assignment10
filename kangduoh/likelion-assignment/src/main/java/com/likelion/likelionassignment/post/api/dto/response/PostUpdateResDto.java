package com.likelion.likelionassignment.post.api.dto.response;

import lombok.Builder;

@Builder
public record PostUpdateResDto(
        String title,
        String content,
        String imageUrl
) {
}
