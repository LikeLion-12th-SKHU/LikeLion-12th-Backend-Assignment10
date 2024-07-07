package com.likelion.likelionassignment.post.domain;

import com.likelion.likelionassignment.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long postId;

    @Column(name = "POST_TITLE", nullable = false)
    private String title;

    @Column(name = "IMAGE_URLS", nullable = false)
    private String imageUrl;

    @Column(name = "POST_CONTENT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    private Post(String title, String imageUrl, String content, User user) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.content = content;
        this.user = user;
    }

    public void updatePost(String title, String content, String imageUrl) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }
}
