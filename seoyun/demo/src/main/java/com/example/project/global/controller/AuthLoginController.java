package com.example.project.global.controller;

import com.example.project.global.dto.Token;
import com.example.project.service.AuthLoginService;
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

    /*@GetMapping("/code/{registrationId}")
    public void google(@RequestParam String code, @PathVariable String registrationId){
        authLoginService.socialLogin(code, registrationId);
    }*/

    @GetMapping("/code/google")
    public Token googleCallback(@RequestParam(name="code") String code) {
        String googleAccessToken = authLoginService.getGoogleAccessToken(code);
        return loginOrSignup(googleAccessToken);
    }

    public Token loginOrSignup(String googleAccessToken){
        return authLoginService.loginOrSignup(googleAccessToken);
    }

}
