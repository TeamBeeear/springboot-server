package com.project.gomgom.user.service;

import com.project.gomgom.user.dto.UserDto;
import com.project.gomgom.user.entity.User;
import com.project.gomgom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.persistence.EntityExistsException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    public UserDto signup(UserDto userDto) {

        Optional<User> existingUser = userRepository.findById(userDto.getUserId());

        // 유저가 이미 존재하는 경우 -> Exception
        if(existingUser.isPresent()) {
            throw new EntityExistsException("이미 존재하는 아이디입니다.");
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
    public UserDto login(UserDto userDto) throws Exception {

        Optional<User> existingUser = userRepository.findById(userDto.getUserId());

        // 유저가 존재하지 않는 경우 -> Exception
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            String pw = user.getUserPw(); // DB 에서 읽어온 비밀번호

            if (userDto.getUserPw().equals(pw)) { // 비밀번호가 일치하는 경우
                return userDto;
            } else { // 비밀번호가 일치하지 않는 경우
                throw new Exception("비밀번호가 올바르지 않습니다.");
            }
        } else { // 유저가 존재하지 않는 경우
            throw new NoSuchElementException("존재하지 않는 아이디입니다.");
        }

    }

}