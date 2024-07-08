package org.likelion.jangsu.article.domain.repository;

import org.likelion.jangsu.article.domain.Article;
import org.likelion.jangsu.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByUser(User user);
}
