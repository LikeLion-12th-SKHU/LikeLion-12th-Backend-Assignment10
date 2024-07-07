package com.example.project.post.api;

import com.example.project.post.api.dto.response.PostResponseDto;
import com.example.project.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    // create
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPost(
            @RequestPart("title") String title,
            @RequestPart("image") MultipartFile image,
            @RequestPart("content") String content) throws IOException {
        postService.createPost(title, image, content);
        return ResponseEntity.ok().build();
    }


    // read
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> posts = postService.findAllPost();
        return ResponseEntity.ok(posts);
    }

    // update
    @PatchMapping("/{postId}")
    public ResponseEntity<Void> updatePost(
            @PathVariable Long postId,
            @RequestPart("title") String title,
            @RequestPart("image") MultipartFile image,
            @RequestPart("content") String content) throws IOException {
        postService.updatePost(postId, title, image, content);
        return ResponseEntity.ok().build();
    }


    // delete
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }
}
