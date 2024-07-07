package com.likelion.likelionassignment.user.api;

import com.likelion.likelionassignment.global.template.ApiResponseTemplate;
import com.likelion.likelionassignment.user.api.dto.response.UserInfoResDto;
import com.likelion.likelionassignment.user.application.UserInfoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/userInfo")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping
    public ResponseEntity<ApiResponseTemplate<UserInfoResDto>> getUserDetails(Principal principal) {

        ApiResponseTemplate<UserInfoResDto> data = userInfoService.getUserInfo(principal);
        return ResponseEntity.status(data.getStatus()).body(data);
    }
}