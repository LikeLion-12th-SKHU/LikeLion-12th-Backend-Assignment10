package com.likelion.oauth2test.post.domain;

import com.likelion.oauth2test.post.api.dto.request.PostUpdateReqDto;
import com.likelion.oauth2test.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID", nullable = false)
    private Long id;

    private String title;
    private String image;
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public Post(String title, String image, String content, User user) {
        this.title = title;
        this.image = image;
        this.content = content;
        this.user = user;
    }

}