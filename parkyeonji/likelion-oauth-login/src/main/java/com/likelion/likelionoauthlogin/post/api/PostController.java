package com.likelion.likelionoauthlogin.post.api;

import com.likelion.likelionoauthlogin.post.api.dto.request.PostSaveReqDto;
import com.likelion.likelionoauthlogin.post.api.dto.response.PostInfoResDto;
import com.likelion.likelionoauthlogin.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostInfoResDto> postSave(PostSaveReqDto postSaveReqDto,
                                           @RequestPart("image")MultipartFile image,
                                           Principal principal) throws IOException {
        PostInfoResDto postInfoResDto = postService.postSave(postSaveReqDto, image, principal);

        return new ResponseEntity<>(postInfoResDto, HttpStatus.CREATED);
    }
}
