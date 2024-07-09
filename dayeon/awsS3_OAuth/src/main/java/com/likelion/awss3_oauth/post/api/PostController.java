package com.likelion.awss3_oauth.post.api;

import com.likelion.awss3_oauth.post.api.dto.PostInfoResDto;
import com.likelion.awss3_oauth.post.api.dto.PostSaveReqDto;
import com.likelion.awss3_oauth.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostInfoResDto> postSave(@RequestPart("post") PostSaveReqDto postSaveReqDto,
                                         @RequestPart("image") MultipartFile image,
                                         Principal principal) throws IOException {
        PostInfoResDto postInfoResDto = postService.postSave(postSaveReqDto, image, principal);

        return new ResponseEntity<>(postInfoResDto, HttpStatus.CREATED);

    }
}
