package com.project.gomgom.heart.service;

import com.project.gomgom.heart.dto.HeartDto;

public interface HeartService {
    Boolean getHeartStatus(String userId, Long postId);
    void insertHeart(HeartDto heartDto);
    void deleteHeart(HeartDto heartDto);
}
