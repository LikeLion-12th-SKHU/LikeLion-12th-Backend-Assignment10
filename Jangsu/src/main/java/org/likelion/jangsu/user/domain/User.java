package org.likelion.jangsu.user.domain;

import ch.qos.logback.classic.spi.TurboFilterList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.jangsu.article.domain.Article;
import org.likelion.jangsu.user.api.dto.request.UserUpdateReqDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name = "user_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userNum;

    private String userId;
<<<<<<< HEAD
    private String email;
    private String name;
=======
    private String password;
    private String email;
    private String name;
    private String url;
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articleList = new ArrayList<>();

    @Builder
<<<<<<< HEAD
    public User(String userId, String email, String name, Role role) {
        this.userId = userId;
        this.email = email;
        this.name = name;
=======
    public User(Integer userNum, String userId, String password, String email, String name, String url, Role role) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.url = url;
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f

        this.role = role;
    }

    public void userInfoUpdate(UserUpdateReqDto userUpdateReqDto) {
        this.userId = userUpdateReqDto.userId();
<<<<<<< HEAD
=======
        this.password = userUpdateReqDto.password();
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
        this.email = userUpdateReqDto.email();
        this.name = userUpdateReqDto.name();
    }
}
