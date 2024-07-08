package org.likelion.jangsu.global.controller;

import lombok.RequiredArgsConstructor;
import org.likelion.jangsu.global.dto.Token;
import org.likelion.jangsu.global.service.AuthLoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login/oauth2")
public class AuthLoginController {
    private final AuthLoginService authLoginService;
/*
    @GetMapping("/code/{registrationId}")
    public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
        authLoginService.socialLogin(code, registrationId);
    }
*/
    @GetMapping("/code/google")
    public Token googleCallBack(@RequestParam(name = "code") String code) {
        String googleAccessToken = authLoginService.getGoogleAccessToken(code);
        return loginOrSignup(googleAccessToken);
    }
    public Token loginOrSignup(String googleAccessToken) {
        return authLoginService.loginOrSignUp(googleAccessToken);
    }
}
