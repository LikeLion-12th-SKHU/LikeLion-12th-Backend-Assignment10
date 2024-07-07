package com.likelion.oauth2test.post.api;

import com.likelion.oauth2test.post.application.PostService;
import com.likelion.oauth2test.post.domain.Post;
import com.likelion.oauth2test.post.dto.request.PostRequestDto;
import com.likelion.oauth2test.post.dto.response.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostResponseDto> postSave(PostRequestDto postRequestDto,
                                                    @RequestPart("image")MultipartFile image,
                                                    Principal principal) throws IOException {
        PostResponseDto postResponseDto = postService.postSave(postRequestDto, image, principal);
        return new ResponseEntity<>(postResponseDto, HttpStatus.CREATED);
    }
}
