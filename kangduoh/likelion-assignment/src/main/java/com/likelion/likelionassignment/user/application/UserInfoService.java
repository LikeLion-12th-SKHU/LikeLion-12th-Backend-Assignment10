package com.likelion.likelionassignment.user.application;

import com.likelion.likelionassignment.global.exception.CustomException;
import com.likelion.likelionassignment.global.exception.code.ErrorCode;
import com.likelion.likelionassignment.global.exception.code.SuccessCode;
import com.likelion.likelionassignment.global.template.ApiResponseTemplate;
import com.likelion.likelionassignment.user.api.dto.response.UserInfoResDto;
import com.likelion.likelionassignment.user.domain.User;
import com.likelion.likelionassignment.user.domain.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public ApiResponseTemplate<UserInfoResDto> getUserInfo(Principal principal) {
        String userEmail = principal.getName();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));

        UserInfoResDto resDto = UserInfoResDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();

        return ApiResponseTemplate.success(SuccessCode.GET_USERINFO_SUCCESS, resDto);
    }
}