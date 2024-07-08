package com.likelion.oauth2test.user.api;

import com.likelion.oauth2test.service.UserService;
import com.likelion.oauth2test.user.api.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserInfo(Principal principal) {
        return new ResponseEntity<>(userService.getUserInfo(principal), HttpStatus.OK);
    }

}