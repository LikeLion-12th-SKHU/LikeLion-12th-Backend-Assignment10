package com.likelion.likelionassignment.global.controller;

import com.likelion.likelionassignment.global.dto.Token;
import com.likelion.likelionassignment.global.service.AuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login/oauth2")
public class AuthLoginController {

    private final AuthLoginService authLoginService;

//    @GetMapping("/code/{registrationId}")
//    public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
//        authLoginService.socialLogin(code, registrationId);
//    }

    @GetMapping("/code/google")
    public Token googleCallback(@RequestParam(name="code") String code) {
        String googleAccessToken = authLoginService.getGoogleAccessToken(code);
        return loginOrSignUp(googleAccessToken);
    }

    public Token loginOrSignUp(String googleAccessToken) {
        return authLoginService.loginOrSignUp(googleAccessToken);
    }
}