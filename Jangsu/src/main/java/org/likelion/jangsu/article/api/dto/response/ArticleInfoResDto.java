package org.likelion.jangsu.article.api.dto.response;

import lombok.Builder;
import org.likelion.jangsu.article.domain.Article;

import java.time.LocalDateTime;

@Builder
public record ArticleInfoResDto(Integer articleId, String articleName, String content, LocalDateTime writeTime) {
    public static ArticleInfoResDto from(Article article) {
        return ArticleInfoResDto.builder()
                .articleId(article.getArticleId())
                .articleName(article.getArticleName())
                .content(article.getContent())
                .writeTime(LocalDateTime.now())
                .build();
    }
}