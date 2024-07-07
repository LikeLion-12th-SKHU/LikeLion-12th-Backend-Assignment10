package com.likelion.likelionassignment.user.application;

import com.likelion.likelionassignment.global.exception.CustomException;
import com.likelion.likelionassignment.global.exception.code.ErrorCode;
import com.likelion.likelionassignment.global.template.ApiResponseTemplate;
import com.likelion.likelionassignment.user.api.dto.response.UserInfoResDto;
import com.likelion.likelionassignment.user.domain.User;
import com.likelion.likelionassignment.user.domain.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public ApiResponseTemplate<UserInfoResDto> getUserInfo(String userName) {
        User user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));

        UserInfoResDto resDto = UserInfoResDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();

        return ApiResponseTemplate.<UserInfoResDto>builder()
                .status(200)
                .success(true)
                .message("사용자 정보 조회 성공")
                .data(resDto)
                .build();
    }
}