package com.project.gomgom.heart.service;

import com.project.gomgom.heart.dto.HeartDto;
import com.project.gomgom.heart.entity.Heart;
import com.project.gomgom.heart.repository.HeartRepository;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.post.repository.PostRepository;
import com.project.gomgom.user.entity.User;
import com.project.gomgom.user.repository.UserRepository;
import com.project.gomgom.util.exception.CustomException;
import com.project.gomgom.util.exception.ErrorCode;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeartServiceImpl implements HeartService{

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final HeartRepository heartRepository;

    @Override
    public Boolean getHeartStatus(String userId, Long postId) {

        // user 가 존재하는지 검증
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        // post 가 존재하는지 검증
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isEmpty()) {
            throw new CustomException(ErrorCode.POST_NOT_FOUND);
        }

        Optional<Heart> existingHeart = heartRepository.findByUserAndPost(userOptional.get(), postOptional.get());
        if (existingHeart.isPresent()) { // 좋아요 눌러져 있다면 true
            return true;
        }
        else { return false; }

    }

    @Override
    public void insertHeart(HeartDto heartDto) {

        // user 가 존재하는지 검증
        Optional<User> userOptional = userRepository.findById(heartDto.getUserId());
        if (userOptional.isEmpty()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        // post 가 존재하는지 검증
        Optional<Post> postOptional = postRepository.findById(heartDto.getPostId());
        if (postOptional.isEmpty()) {
            throw new CustomException(ErrorCode.POST_NOT_FOUND);
        }

        // 이미 눌러져 있는지 검증
        Optional<Heart> existingHeart = heartRepository.findByUserAndPost(userOptional.get(), postOptional.get());
        if (existingHeart.isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_HEARTED); // 이미 눌러져 있다면 BAD_REQUEST 반환
        }

        // 저장
        Heart heart = Heart.builder()
                .post(postRepository.findById(heartDto.getPostId()).get())
                .user(userRepository.findById(heartDto.getUserId()).get())
                .build();

        heartRepository.save(heart);

    }

    @Override
    public void deleteHeart(HeartDto heartDto) {

        // user 가 존재하는지 검증
        Optional<User> userOptional = userRepository.findById(heartDto.getUserId());
        if (userOptional.isEmpty()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        // post 가 존재하는지 검증
        Optional<Post> postOptional = postRepository.findById(heartDto.getPostId());
        if (postOptional.isEmpty()) {
            throw new CustomException(ErrorCode.POST_NOT_FOUND);
        }

        // heart 가 눌러져 있는지 검증
        Optional<Heart> existingHeart = heartRepository.findByUserAndPost(userOptional.get(), postOptional.get());
        if (existingHeart.isEmpty()) {
            throw new CustomException(ErrorCode.EMPTY_HEARTED); // 눌러져 있지 않다면 BAD_REQUEST 반환
        }

        // 눌러져 있는 경우 삭제
        heartRepository.delete(existingHeart.get());

    }

}