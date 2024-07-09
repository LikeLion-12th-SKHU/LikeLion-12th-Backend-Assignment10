package com.likelion.awss3_oauth.post.api.dto;

import com.likelion.awss3_oauth.post.domain.Post;
import lombok.Builder;

@Builder
public record PostInfoResDto(
        Long id,
        String title,
        String imageUrl,
        String content
) {
    public static PostInfoResDto from(Post post) {
        return PostInfoResDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .imageUrl(post.getImageUrl())
                .content(post.getContent())
                .build();
    }
}
