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
=======
<<<<<<< HEAD
    private String email;
    private String name;
=======
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
    private String password;
    private String email;
    private String name;
    private String url;
<<<<<<< HEAD
=======
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articleList = new ArrayList<>();

    @Builder
<<<<<<< HEAD
=======
<<<<<<< HEAD
    public User(String userId, String email, String name, Role role) {
        this.userId = userId;
        this.email = email;
        this.name = name;
=======
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
    public User(Integer userNum, String userId, String password, String email, String name, String url, Role role) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.url = url;
<<<<<<< HEAD
=======
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f

>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
        this.role = role;
    }

    public void userInfoUpdate(UserUpdateReqDto userUpdateReqDto) {
        this.userId = userUpdateReqDto.userId();
<<<<<<< HEAD
        this.password = userUpdateReqDto.password();
=======
<<<<<<< HEAD
=======
        this.password = userUpdateReqDto.password();
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
        this.email = userUpdateReqDto.email();
        this.name = userUpdateReqDto.name();
    }
}
