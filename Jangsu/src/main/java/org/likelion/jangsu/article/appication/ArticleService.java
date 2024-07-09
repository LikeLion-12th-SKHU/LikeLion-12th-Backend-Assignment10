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
<<<<<<< HEAD
import org.likelion.jangsu.global.config.S3Config;
import org.likelion.jangsu.global.service.S3Service;
=======
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
import org.likelion.jangsu.user.domain.User;
import org.likelion.jangsu.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
<<<<<<< HEAD
=======
=======

>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
import java.time.LocalDateTime;
import java.util.List;

@Service
<<<<<<< HEAD
@Transactional(readOnly = false)
public class ArticleService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
=======
@Transactional(readOnly = true)
public class ArticleService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
<<<<<<< HEAD
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
    private final S3Config s3Config;
    private final S3Service s3Service;

    public ArticleService(UserRepository userRepository, ArticleRepository articleRepository, S3Config s3Config, S3Service s3Service) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.s3Config = s3Config;
        this.s3Service = s3Service;
    }

    // 게시글 생성(C, 회원이 아닌 경우 예외 생성)
<<<<<<< HEAD
    public ArticleInfoResDto articleSave(ArticleSaveReqDto articleSaveReqDto, MultipartFile image, Principal principal) throws IOException {
//        User user = userRepository.findById(articleSaveReqDto.user().getUserNum())
//                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_SIGNED_UP,
//                        ErrorCode.USER_NOT_SIGNED_UP.getMessage() + articleSaveReqDto.user().getUserNum())
//                );
        Long id = Long.parseLong(principal.getName());
        User user = userRepository.findById(Math.toIntExact(id)).orElseThrow(()->new RuntimeException("없는 아이디"));

        String url = s3Service.upload(image, "image");
=======
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
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae

        Article article = Article.builder()
                .articleName(articleSaveReqDto.articleName())
                .content(articleSaveReqDto.content())
                .writeTime(LocalDateTime.now())
<<<<<<< HEAD
                .url(url)
=======
<<<<<<< HEAD
                .url(articleSaveReqDto.url())
=======
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
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
                        ErrorCode.USER_NOT_FOUND_EXCEPTION.getMessage()));
=======
<<<<<<< HEAD
                        ErrorCode.USER_NOT_FOUND_EXCEPTION.getMessage() + userNum)
=======
                        ErrorCode.USER_NOT_FOUND_EXCEPTION.getMessage()+ userNum)
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
                );
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
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
