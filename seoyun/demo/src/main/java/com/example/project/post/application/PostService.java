package com.example.project.post.application;

import com.example.project.member.domain.repository.MemberRepository;
import com.example.project.post.api.dto.response.PostResponseDto;
import com.example.project.post.domain.Post;
import com.example.project.post.domain.repository.PostRepository;
import com.example.project.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final S3Service s3Service;

    // create
    @Transactional
    public void createPost(String title, MultipartFile image, String content) throws IOException {
        String imageUrl = s3Service.upload(image, "user");
        Post post = Post.builder()
                .title(title)
                .image(imageUrl)
                .content(content)
                .build();
        postRepository.save(post);
    }
    // read
    public List<PostResponseDto> findAllPost() {
        List<Post> postList = postRepository.findAll();
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for (Post post : postList) {
            postResponseDtoList.add(PostResponseDto.of(post.getId(), post.getTitle(), post.getImage(), post.getContent()));
        }
        return postResponseDtoList;
    }

    // update
    @Transactional
    public void updatePost(Long id, String title, MultipartFile image, String content) throws IOException {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다"));

        String imageUrl = s3Service.upload(image, "post");
        post.updatePost(title, imageUrl, content);
        postRepository.save(post);
    }
    // delete
    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다"));
        postRepository.delete(post);
    }
}
