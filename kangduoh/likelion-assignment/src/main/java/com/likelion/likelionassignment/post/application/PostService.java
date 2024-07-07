package com.likelion.likelionassignment.post.application;

import com.likelion.likelionassignment.global.exception.CustomException;
import com.likelion.likelionassignment.global.exception.code.ErrorCode;
import com.likelion.likelionassignment.global.exception.code.SuccessCode;
import com.likelion.likelionassignment.global.service.S3Service;
import com.likelion.likelionassignment.global.template.ApiResponseTemplate;
import com.likelion.likelionassignment.post.api.dto.PostDto;
import com.likelion.likelionassignment.post.api.dto.request.PostCreateReqDto;
import com.likelion.likelionassignment.post.api.dto.request.PostUpdateReqDto;
import com.likelion.likelionassignment.post.api.dto.response.PostCreateResDto;
import com.likelion.likelionassignment.post.api.dto.response.PostUpdateResDto;
import com.likelion.likelionassignment.post.domain.Post;
import com.likelion.likelionassignment.post.domain.repository.PostRepository;
import com.likelion.likelionassignment.user.domain.User;
import com.likelion.likelionassignment.user.domain.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;


    @Transactional
    public ApiResponseTemplate<PostCreateResDto> createPost(Principal principal, PostCreateReqDto reqDto, MultipartFile image) throws IOException {
        User user = getAuthenticatedUser(principal);
        String imageUrl = s3Service.upload(image, "post");

        Post post = Post.builder()
                .title(reqDto.title())
                .content(reqDto.content())
                .imageUrl(imageUrl)
                .user(user)
                .build();

        postRepository.save(post);

        PostCreateResDto resDto = PostCreateResDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .build();

        return ApiResponseTemplate.success(SuccessCode.CREATE_POST_SUCCESS, resDto);
    }

    @Transactional(readOnly = true)
    public ApiResponseTemplate<List<PostDto>> getAllPosts(Principal principal) {
        getAuthenticatedUser(principal);
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos = posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ApiResponseTemplate.success(SuccessCode.GET_ALL_POSTS_SUCCESS, postDtos);
    }

    @Transactional(readOnly = true)
    public ApiResponseTemplate<PostDto> getPostById(Long postId, Principal principal) {
        getAuthenticatedUser(principal);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST_EXCEPTION, ErrorCode.NOT_FOUND_POST_EXCEPTION.getMessage()));

        PostDto postDto = convertToDto(post);

        return ApiResponseTemplate.success(SuccessCode.GET_POST_SUCCESS, postDto);
    }

    @Transactional
    public ApiResponseTemplate<PostUpdateResDto> updatePost(Principal principal, Long postId, PostUpdateReqDto reqDto, MultipartFile image) throws IOException {
        User user = getAuthenticatedUser(principal);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST_EXCEPTION, ErrorCode.NOT_FOUND_POST_EXCEPTION.getMessage()));

        if (!post.getUser().getEmail().equals(user.getEmail())) {
            throw new CustomException(ErrorCode.ONLY_OWN_POST_MODIFY_EXCEPTION, ErrorCode.ONLY_OWN_POST_MODIFY_EXCEPTION.getMessage());
        }

        String imageUrl = s3Service.upload(image, "post");
        post.updatePost(reqDto.title(), reqDto.content(), imageUrl);

        PostUpdateResDto resDto = PostUpdateResDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .build();

        return ApiResponseTemplate.success(SuccessCode.UPDATE_POST_SUCCESS, resDto);
    }

    @Transactional
    public ApiResponseTemplate<Void> deletePost(Principal principal, Long postId) {
        User user = getAuthenticatedUser(principal);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST_EXCEPTION, ErrorCode.NOT_FOUND_POST_EXCEPTION.getMessage()));

        if (!post.getUser().getEmail().equals(user.getEmail())) {
            throw new CustomException(ErrorCode.ONLY_OWN_POST_MODIFY_EXCEPTION, ErrorCode.ONLY_OWN_POST_MODIFY_EXCEPTION.getMessage());
        }

        postRepository.delete(post);

        return ApiResponseTemplate.success(SuccessCode.DELETE_POST_SUCCESS, null);
    }

    private User getAuthenticatedUser(Principal principal) {
        String userEmail = principal.getName();
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));
    }

    private PostDto convertToDto(Post post) {
        return new PostDto(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getImageUrl()
        );
    }
}
