package org.likelion.jangsu.article.api.dto;


import jakarta.validation.Valid;
import org.likelion.jangsu.article.api.dto.request.ArticleSaveReqDto;
import org.likelion.jangsu.article.api.dto.response.ArticleInfoResDto;
import org.likelion.jangsu.article.api.dto.response.ArticleListResDto;
import org.likelion.jangsu.article.appication.ArticleService;
import org.likelion.jangsu.common.dto.BaseResponse;
import org.likelion.jangsu.common.error.SuccessCode;
import org.springframework.http.HttpStatus;
<<<<<<< HEAD
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
    // 게시글 생성(C)
<<<<<<< HEAD
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<ArticleInfoResDto> articleSave(@RequestPart("post") @Valid ArticleSaveReqDto articleSaveReqDto,
                                                       @RequestPart("image")MultipartFile image,
                                                       Principal principal) throws IOException {
        ArticleInfoResDto articleInfoResDto = articleService.articleSave(articleSaveReqDto, image, principal);
=======
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<ArticleInfoResDto> articleSave(@RequestBody @Valid ArticleSaveReqDto articleSaveReqDto) {
        ArticleInfoResDto articleInfoResDto = articleService.articleSave(articleSaveReqDto);
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
        return BaseResponse.success(SuccessCode.ARTICLE_SAVE_SUCCESS, articleInfoResDto);
    }
    // 게시글 하나만 조회(R1)
    @GetMapping("/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ArticleInfoResDto> articleFindOne(@PathVariable("articleId")Integer articleId) {
        ArticleInfoResDto articleFindOne = articleService.articleFindOne(articleId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, articleFindOne);
    }

    // 게시글 ID에 따라 전체 조회(R2)
    @GetMapping("/{userNum}/all")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ArticleListResDto> articleFindById(@PathVariable("userNum") Integer userNum) {
        ArticleListResDto articleListResDto = articleService.articleFindById(userNum);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, articleListResDto);
    }

    // 게시글 전체 조회(R3)
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ArticleListResDto> articleFindAll() {
        ArticleListResDto articleListResDto = articleService.articleFindAll();
        return BaseResponse.success(SuccessCode.GET_SUCCESS, articleListResDto);
    }

    @DeleteMapping("/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ArticleInfoResDto> articleDelete(@PathVariable("articleId")Integer articleId) {
        ArticleInfoResDto articleInfoResDto = articleService.articleDelete(articleId);
        return BaseResponse.success(SuccessCode.ARTICLE_DELETE_SUCCESS, articleInfoResDto);
    }
}
