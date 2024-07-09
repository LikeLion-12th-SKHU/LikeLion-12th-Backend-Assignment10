package org.likelion.jangsu.global.controller;

import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import org.likelion.jangsu.common.error.ErrorCode;
=======
<<<<<<< HEAD
import org.likelion.jangsu.common.error.ErrorCode;
import org.likelion.jangsu.common.exception.CustomException;
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
import org.likelion.jangsu.global.dto.Token;
import org.likelion.jangsu.global.service.AuthLoginService;
import org.likelion.jangsu.user.domain.User;
import org.likelion.jangsu.user.domain.repository.UserRepository;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


=======
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

=======
import org.likelion.jangsu.global.dto.Token;
import org.likelion.jangsu.global.service.AuthLoginService;
import org.springframework.web.bind.annotation.*;

>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
@RestController
@RequiredArgsConstructor
@RequestMapping("/login/oauth2")
public class AuthLoginController {
    private final AuthLoginService authLoginService;
<<<<<<< HEAD
    private final UserRepository userRepository;

=======
<<<<<<< HEAD
    private final UserRepository userRepository;
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
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
<<<<<<< HEAD

    /*
        @GetMapping("/code/{registrationId}")
        public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
            authLoginService.socialLogin(code, registrationId);
        }
    */
=======
=======
/*
    @GetMapping("/code/{registrationId}")
    public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
        authLoginService.socialLogin(code, registrationId);
    }
*/
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
    @GetMapping("/code/google")
    public Token googleCallBack(@RequestParam(name = "code") String code) {
        String googleAccessToken = authLoginService.getGoogleAccessToken(code);
        return loginOrSignup(googleAccessToken);
    }
<<<<<<< HEAD

=======
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
    public Token loginOrSignup(String googleAccessToken) {
        return authLoginService.loginOrSignUp(googleAccessToken);
    }
}
