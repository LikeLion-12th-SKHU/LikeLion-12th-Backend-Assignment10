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
    private String url;
    private LocalDateTime writeTime = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num")
    private User user;

    @Builder
    private Article(String articleName, String content, String url, LocalDateTime writeTime, User user) {
        this.articleName = articleName;
        this.content = content;
        this.url = url;
        this.writeTime = writeTime;
        this.user = user;
    }

    public void articleUpdate (ArticleUpdateReqDto articleUpdateReqDto) {
        this.articleName = articleUpdateReqDto.articleName();
        this.content=articleUpdateReqDto.content();
        this.writeTime = articleUpdateReqDto.writeTime();
    }
}
