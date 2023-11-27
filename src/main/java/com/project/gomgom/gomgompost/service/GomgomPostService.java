package com.project.gomgom.gomgompost.service;

import com.project.gomgom.gomgompost.dto.GomgomPostAllResDto;
import com.project.gomgom.gomgompost.dto.GomgomPostOneResDto;
import com.project.gomgom.gomgompost.dto.GomgomPostReqDto;
import java.util.Collection;

public interface GomgomPostService {
    void createPost(GomgomPostReqDto gomgomPostReqDto); // 게시글 생성
    Collection<GomgomPostAllResDto> readAllPost(); // 게시글 전체 조회
    GomgomPostOneResDto readOnePost(Long id); // 게시글 상세 조회
}
