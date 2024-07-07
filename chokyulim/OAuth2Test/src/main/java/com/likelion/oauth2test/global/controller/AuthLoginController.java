package com.likelion.oauth2test.global.controller;

import com.likelion.oauth2test.global.dto.Token;
import com.likelion.oauth2test.service.AuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login/oauth2")
public class AuthLoginController {
    private final AuthLoginService authLoginService;

    // 콘솔에 출력
//    @GetMapping("/code/{registrationId}") // registraionId = google인가코드
//    public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
//        authLoginService.socialLogin(code, registrationId);
//    }

    @GetMapping("/code/google") // 인가 코드 받음
    public Token googleCallback(@RequestParam(name = "code") String code) {
        String googleAccessToken = authLoginService.getGoogleAccessToken(code);
        return loginOrSignUp(googleAccessToken);
    }

    public Token loginOrSignUp(String googleAccessToken) { // 인가 코드를 통해 로그인이나 회원가입 하도록 함
        return authLoginService.loginOrSignUp(googleAccessToken);
    }
}