package com.likelion.awss3_oauth.user.application;

import com.likelion.awss3_oauth.s3.S3Service;
import com.likelion.awss3_oauth.user.api.dto.UserResponseDto;
import com.likelion.awss3_oauth.user.domain.User;
import com.likelion.awss3_oauth.user.domain.repository.UserRepository;
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
public class UserService {

    private final UserRepository userRepository;
    private final S3Service s3Service;

    @Transactional
    public void createUser(String name, MultipartFile image, String content) throws IOException {
        String imageUrl = s3Service.upload(image, "user");
        User user = User.builder()
                .name(name)
                .image(imageUrl)
                .content(content)
                .build();
        userRepository.save(user);
    }

    public List<UserResponseDto> findAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for (User user : userList) {
            userResponseDtoList.add(UserResponseDto.of(user.getId(), user.getName(), user.getImage(), user.getContent()));
        }
        return userResponseDtoList;
    }

    @Transactional
    public void updateUser(Long id, String name, MultipartFile image) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다"));

        String imageUrl = s3Service.upload(image, "user");
        user.updateUser(name, imageUrl);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다"));
        userRepository.delete(user);
    }
}
