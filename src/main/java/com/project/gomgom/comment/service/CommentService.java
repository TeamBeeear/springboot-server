package com.project.gomgom.comment.service;

import com.project.gomgom.comment.dto.CommentReqDto;
import com.project.gomgom.comment.dto.CommentResDto;
import com.project.gomgom.comment.entity.Comment;

import java.util.Collection;

public interface CommentService {
    Comment createComment(CommentReqDto commentReqDto);
    Collection<CommentResDto> readComments(Long boardId, Long postId);
}
