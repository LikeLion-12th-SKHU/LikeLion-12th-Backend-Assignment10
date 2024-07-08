package org.likelion.jangsu.global.controller;

import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import org.likelion.jangsu.common.error.ErrorCode;
import org.likelion.jangsu.common.exception.CustomException;
import org.likelion.jangsu.global.dto.Token;
import org.likelion.jangsu.global.service.AuthLoginService;
import org.likelion.jangsu.user.domain.User;
import org.likelion.jangsu.user.domain.repository.UserRepository;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

=======
import org.likelion.jangsu.global.dto.Token;
import org.likelion.jangsu.global.service.AuthLoginService;
import org.springframework.web.bind.annotation.*;

>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
@RestController
@RequiredArgsConstructor
@RequestMapping("/login/oauth2")
public class AuthLoginController {
    private final AuthLoginService authLoginService;
<<<<<<< HEAD
    private final UserRepository userRepository;
    /*
        @GetMapping("/code/{registrationId}")
        public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
            authLoginService.socialLogin(code, registrationId);
        }
    */
    @GetMapping("/test")
    public User test(Principal principal) {
        Integer id = Integer.parseInt(principal.getName());

        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(ErrorCode.USER_NOT_SIGNED_UP.getMessage()));
    }
=======
/*
    @GetMapping("/code/{registrationId}")
    public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
        authLoginService.socialLogin(code, registrationId);
    }
*/
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
    @GetMapping("/code/google")
    public Token googleCallBack(@RequestParam(name = "code") String code) {
        String googleAccessToken = authLoginService.getGoogleAccessToken(code);
        return loginOrSignup(googleAccessToken);
    }
    public Token loginOrSignup(String googleAccessToken) {
        return authLoginService.loginOrSignUp(googleAccessToken);
    }
}
