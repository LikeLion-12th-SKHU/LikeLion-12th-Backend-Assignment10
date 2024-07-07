package com.likelion.oauth2test.post.api;

import com.likelion.oauth2test.post.api.dto.request.PostSaveReqDto;
import com.likelion.oauth2test.post.api.dto.request.PostUpdateReqDto;
import com.likelion.oauth2test.post.api.dto.response.PostInfoResDto;
import com.likelion.oauth2test.post.api.dto.response.PostListResDto;
import com.likelion.oauth2test.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<PostListResDto> postfindAll(Principal principal) {
        PostListResDto postListResDto = postService.postFindAll(principal);
        return new ResponseEntity<>(postListResDto, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostInfoResDto> postFindOne(@PathVariable Long postId) {
        return new ResponseEntity<>(postService.postFindOne(postId), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostInfoResDto> postSave(@RequestPart("post") PostSaveReqDto postSaveReqDto,
                                           @RequestPart("image") MultipartFile image,
                                           Principal principal) throws IOException {
        PostInfoResDto postInfoResDto = postService.postSave(postSaveReqDto, image, principal);
        return new ResponseEntity<>(postInfoResDto, HttpStatus.CREATED);

    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>("게시글이 삭제되었습니다.", HttpStatus.OK);
    }

    @PatchMapping(value = "/{postId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostInfoResDto> updatePost(@PathVariable("postId") Long postId,
                                             @RequestPart("post") PostUpdateReqDto postUpdateReqDto,
                                             @RequestPart("image") MultipartFile image,
                                             Principal principal) throws IOException{
        PostInfoResDto postInfoResDto = postService.updatePost(postId, postUpdateReqDto, image, principal);
        return new ResponseEntity<>(postInfoResDto, HttpStatus.OK);
    }

}
