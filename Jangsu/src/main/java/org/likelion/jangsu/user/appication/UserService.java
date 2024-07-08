package org.likelion.jangsu.user.appication;

import org.likelion.jangsu.common.error.ErrorCode;
import org.likelion.jangsu.common.exception.NotFoundException;
import org.likelion.jangsu.user.api.dto.request.UserSaveReqDto;
import org.likelion.jangsu.user.api.dto.request.UserUpdateReqDto;
import org.likelion.jangsu.user.api.dto.response.UserInfoResDto;
import org.likelion.jangsu.user.domain.User;
import org.likelion.jangsu.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 사용자 생성(C, 회원가입)
    public UserInfoResDto userSave(UserSaveReqDto userSaveReqDto) {
        User user = User.builder()
                .userId(userSaveReqDto.userId())
                .email(userSaveReqDto.email())
                .role(userSaveReqDto.role())
                .build();

        userRepository.save(user);
        return UserInfoResDto.from(user);
    }

    // 사용자 한 명 검색하기(R1)
    public UserInfoResDto userFindOne(Integer userNum) {
        User user = userRepository.findById(userNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. ID : " + userNum));
        return UserInfoResDto.from(user);
    }

    // 시용자 전부 찾기(R2)
    public List<UserInfoResDto> userFindAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserInfoResDto::from).collect(Collectors.toList());
    }

    // 사용자 업데이트(U, 회원 정보 수정)
    public UserInfoResDto userUpdate(Integer userNum, UserUpdateReqDto userUpdateReqDto) {
        User user = userRepository.findById(userNum)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND_EXCEPTION,
                        ErrorCode.USER_NOT_FOUND_EXCEPTION.getMessage() + userNum)
                );

        user.userInfoUpdate(userUpdateReqDto);
        return UserInfoResDto.from(user);
    }

    // 사용자 삭제(D, 회원탈퇴)
    public UserInfoResDto userDelete(Integer userNum) {
        User user = userRepository.findById(userNum)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND_EXCEPTION,
                        ErrorCode.USER_NOT_FOUND_EXCEPTION.getMessage() + userNum)
                );

        userRepository.delete(user);
        return UserInfoResDto.from(user);
    }
}
