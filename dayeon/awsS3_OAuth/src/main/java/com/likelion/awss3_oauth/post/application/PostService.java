package com.likelion.awss3_oauth.post.application;

import com.likelion.awss3_oauth.post.api.dto.PostInfoResDto;
import com.likelion.awss3_oauth.post.api.dto.PostSaveReqDto;
import com.likelion.awss3_oauth.post.domain.Post;
import com.likelion.awss3_oauth.user.domain.User;
import com.likelion.awss3_oauth.post.domain.repository.PostRepository;
import com.likelion.awss3_oauth.s3.S3Service;
import com.likelion.awss3_oauth.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;

    @Transactional
    public PostInfoResDto postSave(PostSaveReqDto postSaveReqDto, MultipartFile multipartFile, Principal principal) throws IOException {
        String image = s3Service.upload(multipartFile, "post");
        Long id = Long.parseLong(principal.getName());

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));

        Post post = postSaveReqDto.toEntity(user, image);
        postRepository.save(post);

        return PostInfoResDto.from(post);
    }
}
