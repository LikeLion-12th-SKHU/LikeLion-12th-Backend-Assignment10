package org.likelion.jangsu.article.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.jangsu.article.api.dto.request.ArticleUpdateReqDto;
import org.likelion.jangsu.user.domain.User;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Integer articleId;

    private String articleName;
    private String content;
<<<<<<< HEAD
    private String url;
=======
<<<<<<< HEAD
    private String url;
=======
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
    private LocalDateTime writeTime = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num")
    private User user;

    @Builder
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
    private Article(String articleName, String content, String url, LocalDateTime writeTime, User user) {
        this.articleName = articleName;
        this.content = content;
        this.url = url;
<<<<<<< HEAD
=======
=======
    private Article(String articleName, String content, LocalDateTime writeTime, User user) {
        this.articleName = articleName;
        this.content = content;
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
        this.writeTime = writeTime;
        this.user = user;
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
    }

    public void articleUpdate (ArticleUpdateReqDto articleUpdateReqDto) {
        this.articleName = articleUpdateReqDto.articleName();
        this.content=articleUpdateReqDto.content();
        this.writeTime = articleUpdateReqDto.writeTime();
    }
}
