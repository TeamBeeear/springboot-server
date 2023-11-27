package com.project.gomgom.user.service;

import com.project.gomgom.user.dto.UserDto;
import com.project.gomgom.user.entity.User;
import com.project.gomgom.user.repository.UserRepository;
import com.project.gomgom.util.exception.CustomException;
import com.project.gomgom.util.exception.ErrorCode;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    // 회원가입
    @Override
    public UserDto signup(UserDto userDto) {

        Optional<User> existingUser = userRepository.findById(userDto.getUserId());

        // 유저가 이미 존재하는 경우 -> Exception
        if(existingUser.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_USER);
        }

        // id 에 값이 없는 경우
        if (userDto.getUserId() == null
                || userDto.getUserId().isEmpty()
                || userDto.getUserId().isBlank()) {
            throw new CustomException(ErrorCode.BAD_ID);
        }

        // pw 에 값이 없는 경우
        if (userDto.getUserPw() == null
                || userDto.getUserPw().isEmpty()
                || userDto.getUserPw().isBlank()) {
            throw new CustomException(ErrorCode.BAD_PASSWORD);
        }

        // 유저가 존재하지 않는 경우 -> 정상 처리
        User newUser = User.builder()
                .userId(userDto.getUserId())
                .userPw(userDto.getUserPw())
                .build();

        User user = userRepository.save(newUser);
        return userDto;
    }

    // 로그인
    @Override
    public UserDto login(UserDto userDto) {

        Optional<User> existingUser = userRepository.findById(userDto.getUserId());

        // id 에 값이 없는 경우
        if (userDto.getUserId() == null
                || userDto.getUserId().isEmpty()
                || userDto.getUserId().isBlank()) {
            throw new CustomException(ErrorCode.BAD_ID);
        }

        // pw 에 값이 없는 경우
        if (userDto.getUserPw() == null
                || userDto.getUserPw().isEmpty()
                || userDto.getUserPw().isBlank()) {
            throw new CustomException(ErrorCode.BAD_PASSWORD);
        }

        // 유저가 존재하는 경우
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            String pw = user.getUserPw(); // DB 에서 읽어온 비밀번호

            if (userDto.getUserPw().equals(pw)) { // 비밀번호가 일치하는 경우
                return userDto;
            } else { // 비밀번호가 일치하지 않는 경우
                throw new CustomException(ErrorCode.INVALID_PASSWORD);
            }
        } else { // 유저가 존재하지 않는 경우
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

    }

}