package com.project.gomgom.comment.service;

import com.project.gomgom.comment.dto.CommentGomgomReqDto;
import com.project.gomgom.comment.dto.CommentUserReqDto;
import com.project.gomgom.comment.dto.CommentResDto;
import com.project.gomgom.comment.entity.Comment;

import java.util.Collection;

public interface CommentService {
    Comment createUserPostComment(CommentUserReqDto commentUserReqDto);
    Comment createGomgomPostComment(CommentGomgomReqDto dto);
    Collection<CommentResDto> readUserPostComments(Long boardId, Long postId);
    Collection<CommentResDto> readGomgomPostComments(Long gomgomPostId);
}