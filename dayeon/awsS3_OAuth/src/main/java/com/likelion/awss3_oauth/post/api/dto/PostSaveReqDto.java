package com.likelion.awss3_oauth.post.api.dto;

import com.likelion.awss3_oauth.post.domain.Post;
import com.likelion.awss3_oauth.user.domain.User;

public record PostSaveReqDto(
        Long userId,
        String title,
        String content
) {
    public Post toEntity(User user, String image) {
        return Post.builder()
                .title(title)
                .content(content)
                .imageUrl(image)
                .user(user)
                .build();
    }

}
