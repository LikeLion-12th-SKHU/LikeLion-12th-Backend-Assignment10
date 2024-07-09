package com.likelion.oauth2test.post.application;

import com.likelion.oauth2test.global.service.S3Service;
import com.likelion.oauth2test.post.api.dto.request.PostSaveReqDto;
import com.likelion.oauth2test.post.api.dto.response.PostInfoResDto;
import com.likelion.oauth2test.post.domain.repository.PostRepository;
import com.likelion.oauth2test.post.domain.Post;
import com.likelion.oauth2test.user.domain.User;
import com.likelion.oauth2test.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;

    @Transactional
    public PostInfoResDto savePost(PostSaveReqDto postSaveReqDto, MultipartFile multipartFile,
                                   Principal principal) throws IOException {
        Long id = Long.parseLong(principal.getName());
        String image = s3Service.upload(multipartFile, "post");

        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자가 없습니다. id =" + id));

        Post post = Post.builder()
                .title(postSaveReqDto.title())
                .content(postSaveReqDto.content())
                .image(image)
                .user(user)
                .build();

        postRepository.save(post);

        return PostInfoResDto.from(post);
    }

}