package org.likelion.jangsu.user.api.dto;

import jakarta.validation.Valid;
import org.likelion.jangsu.common.dto.BaseResponse;
import org.likelion.jangsu.common.error.SuccessCode;
import org.likelion.jangsu.user.api.dto.request.UserSaveReqDto;
import org.likelion.jangsu.user.api.dto.request.UserUpdateReqDto;
import org.likelion.jangsu.user.api.dto.response.UserInfoResDto;
import org.likelion.jangsu.user.api.dto.response.UserListResDto;
import org.likelion.jangsu.user.appication.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 사용자 저장(C ,회원가입)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<UserInfoResDto> userSave(@RequestBody @Valid UserSaveReqDto userSaveReqDto) {
        UserInfoResDto userInfoResDto = userService.userSave(userSaveReqDto);
        return BaseResponse.success(SuccessCode.USER_SAVE_SUCCESS, userInfoResDto);
    }

    // 사용자 한명 찾기(R1)
    @GetMapping("/{userNum}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<UserInfoResDto> userFindOne(@PathVariable("userNum") Integer userNum) {
        UserInfoResDto userInfoResDto = userService.userFindOne(userNum);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, userInfoResDto);
    }

    // 사용자 전부 찾기(R2)
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<UserListResDto> userFindAll() {
        List<UserInfoResDto> users = userService.userFindAll();
        UserListResDto userListResDto = UserListResDto.from(users);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, userListResDto);
    }

    // 사용자 정보 수정(U)
    @PatchMapping("/{userNum}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<UserInfoResDto> userUpdate(@PathVariable("userNum") Integer userNum,
                                                   @RequestBody @Valid UserUpdateReqDto userUpdateReqDto) {
        UserInfoResDto userInfoResDto = userService.userUpdate(userNum, userUpdateReqDto);
        return BaseResponse.success(SuccessCode.USER_UPDATE_SUCCESS, userInfoResDto);
    }

    // 사용자 정보 삭제(D)
    @DeleteMapping("/{userNum}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<UserInfoResDto> userDelete(@PathVariable("userNum") Integer userNum) {
        UserInfoResDto userInfoResDto = userService.userDelete(userNum);
        return BaseResponse.success(SuccessCode.USER_DELETE_SUCCESS, userInfoResDto);
    }
}