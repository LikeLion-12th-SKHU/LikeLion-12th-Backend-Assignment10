package org.likelion.jangsu.article.appication;

import org.likelion.jangsu.article.api.dto.request.ArticleSaveReqDto;
import org.likelion.jangsu.article.api.dto.request.ArticleUpdateReqDto;
import org.likelion.jangsu.article.api.dto.response.ArticleInfoResDto;
import org.likelion.jangsu.article.api.dto.response.ArticleListResDto;
import org.likelion.jangsu.article.domain.Article;
import org.likelion.jangsu.article.domain.repository.ArticleRepository;
import org.likelion.jangsu.common.error.ErrorCode;
import org.likelion.jangsu.common.exception.NotFoundException;
<<<<<<< HEAD
import org.likelion.jangsu.global.config.S3Config;
import org.likelion.jangsu.global.service.S3Service;
=======
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
import org.likelion.jangsu.user.domain.User;
import org.likelion.jangsu.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
<<<<<<< HEAD
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
=======

>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArticleService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
<<<<<<< HEAD
    private final S3Config s3Config;
    private final S3Service s3Service;

    public ArticleService(UserRepository userRepository, ArticleRepository articleRepository, S3Config s3Config, S3Service s3Service) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.s3Config = s3Config;
        this.s3Service = s3Service;
    }

    // 게시글 생성(C, 회원이 아닌 경우 예외 생성)
    public ArticleInfoResDto articleSave(
            ArticleSaveReqDto articleSaveReqDto, MultipartFile multipartFile, Principal principal) throws IOException {
        String image = s3Service.upload(multipartFile, "article");
        Integer id = Integer.parseInt(principal.getName());

                User user = userRepository.findById(articleSaveReqDto.user().getUserNum())
=======

    public ArticleService(UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    // 게시글 생성(C, 회원이 아닌 경우 예외 생성)
    public ArticleInfoResDto articleSave(ArticleSaveReqDto articleSaveReqDto) {
        User user = userRepository.findById(articleSaveReqDto.user().getUserNum())
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_SIGNED_UP,
                        ErrorCode.USER_NOT_SIGNED_UP.getMessage() + articleSaveReqDto.user().getUserNum())
                );

        Article article = Article.builder()
                .articleName(articleSaveReqDto.articleName())
                .content(articleSaveReqDto.content())
                .writeTime(LocalDateTime.now())
<<<<<<< HEAD
                .url(articleSaveReqDto.url())
=======
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
                .user(user)
                .build();

        articleRepository.save(article);
        return ArticleInfoResDto.from(article);
    }

    // 게시글 조회(R1, 하나만 조회)
    public ArticleInfoResDto articleFindOne(Integer articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.ARTICLES_NOT_FOUND_EXCEPTION,
                        ErrorCode.ARTICLES_NOT_FOUND_EXCEPTION.getMessage() + articleId)
                );

        return ArticleInfoResDto.from(article);
    }

    // 게시글 조회(R2, 사용자 ID에 따라 조회)
    public ArticleListResDto articleFindById(Integer userNum) {
        User user = userRepository.findById(userNum)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND_EXCEPTION,
<<<<<<< HEAD
                        ErrorCode.USER_NOT_FOUND_EXCEPTION.getMessage() + userNum)
=======
                        ErrorCode.USER_NOT_FOUND_EXCEPTION.getMessage()+ userNum)
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
                );
        List<Article> articles = articleRepository.findByUser(user);
        List<ArticleInfoResDto> articleInfoResDtoList = articles.stream()
                .map(ArticleInfoResDto::from).toList();

        return ArticleListResDto.from(articleInfoResDtoList);
    }

    // 게시글 조회(R3, 전체 조회)
    public ArticleListResDto articleFindAll() {
        List<Article> articles = articleRepository.findAll();

        List<ArticleInfoResDto> articleInfoResDtoList = articles.stream()
                .map(ArticleInfoResDto::from).toList();

        return ArticleListResDto.from(articleInfoResDtoList);
    }

    // 게시글 수정(U)
    public ArticleInfoResDto articleUpdate(Integer articleId, ArticleUpdateReqDto articleUpdateReqDto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND_EXCEPTION,
                        ErrorCode.USER_NOT_FOUND_EXCEPTION.getMessage() + articleId)
                );

        article.articleUpdate(articleUpdateReqDto);
        return ArticleInfoResDto.from(article);
    }

    // 게시글 삭제(D)
    public ArticleInfoResDto articleDelete(Integer articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.ARTICLES_NOT_FOUND_EXCEPTION,
                        ErrorCode.ARTICLES_NOT_FOUND_EXCEPTION.getMessage() + articleId)
                );

        articleRepository.delete(article);
        return ArticleInfoResDto.from(article);
    }
}
