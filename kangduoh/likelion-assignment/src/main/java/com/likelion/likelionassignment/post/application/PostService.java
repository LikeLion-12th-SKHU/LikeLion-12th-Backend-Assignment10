package com.likelion.likelionassignment.post.application;

import com.likelion.likelionassignment.global.exception.CustomException;
import com.likelion.likelionassignment.global.exception.code.ErrorCode;
import com.likelion.likelionassignment.global.service.S3Service;
import com.likelion.likelionassignment.global.template.ApiResponseTemplate;
import com.likelion.likelionassignment.post.api.dto.PostDto;
import com.likelion.likelionassignment.post.api.dto.request.PostCreateReqDto;
import com.likelion.likelionassignment.post.api.dto.request.PostUpdateReqDto;
import com.likelion.likelionassignment.post.api.dto.response.PostCreateResDto;
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
        String userEmail = principal.getName();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER_EXCEPTION, "해당 이메일을 가진 사용자를 찾을 수 없습니다: " + userEmail));

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

        return ApiResponseTemplate.<PostCreateResDto>builder()
                .status(201)
                .success(true)
                .message("게시글 작성 성공")
                .data(resDto)
                .build();
    }

    @Transactional(readOnly = true)
    public ApiResponseTemplate<List<PostDto>> getAllPosts(Principal principal) {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos = posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ApiResponseTemplate.<List<PostDto>>builder()
                .status(200)
                .success(true)
                .message("모든 게시글 조회 성공")
                .data(postDtos)
                .build();
    }

    @Transactional(readOnly = true)
    public ApiResponseTemplate<PostDto> getPostById(Long postId, Principal principal) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST_EXCEPTION, "게시글을 찾을 수 없습니다: " + postId));

        PostDto postDto = convertToDto(post);

        return ApiResponseTemplate.<PostDto>builder()
                .status(200)
                .success(true)
                .message("특정 게시글 조회 성공")
                .data(postDto)
                .build();
    }

    @Transactional
    public ApiResponseTemplate<Void> updatePost(Principal principal, Long postId, PostUpdateReqDto reqDto, MultipartFile image) throws IOException {
        String userEmail = principal.getName();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST_EXCEPTION, "해당 게시글을 찾을 수 없습니다."));

        if (!post.getUser().getEmail().equals(userEmail)) {
            throw new CustomException(ErrorCode.ONLY_OWN_POST_MODIFY_EXCEPTION, "본인이 작성한 게시글만 수정 가능합니다.");
        }

        String imageUrl = s3Service.upload(image, "post");
        post.updatePost(reqDto.title(), reqDto.content(), imageUrl);

        return ApiResponseTemplate.<Void>builder()
                .status(200)
                .success(true)
                .message("게시글 수정 성공")
                .build();
    }

    @Transactional
    public ApiResponseTemplate<Void> deletePost(Principal principal, Long postId) {
        String userEmail = principal.getName();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST_EXCEPTION, "해당 게시글을 찾을 수 없습니다: " + postId));

        if (!post.getUser().getEmail().equals(userEmail)) {
            throw new CustomException(ErrorCode.ONLY_OWN_POST_MODIFY_EXCEPTION, "본인이 작성한 게시글만 삭제 가능합니다.");
        }

        postRepository.delete(post);

        return ApiResponseTemplate.<Void>builder()
                .status(200)
                .success(true)
                .message("게시글 삭제 성공")
                .build();
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
