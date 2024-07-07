package com.example.project.post.domain;

import com.example.project.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @Column(name = "POST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "POST_TITLE", nullable = false)
    private String title;

    @Column(name = "POST_IMAGE", nullable = false)
    private String image;

    @Column(name = "POST_CONTENT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


    @Builder
    public Post(Long id, String title, String image, String content, Member member) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.content = content;
        this.member = member;
    }

    public void updatePost(String title, String image, String content) {
        this.title = title;
        this.image = image;
        this.content = content;
    }
}
