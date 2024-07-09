package com.likelion.awss3_oauth.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String name;

    @Column(name = "USER_EMAIL", nullable = false)
    private String email;

    @Column(name = "USER_PICTURE_URL")
    private String image;

    @Column(name = "USER_CONTENT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false)
    private Role role;

    @Builder
    public User(Long id, String name, String email, String image, String content, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.image = image;
        this.content = content;
        this.role = role;
    }

    public void updateUser(String name, String image) {
        this.name = name;
        this.image = image;
    }
}
