package com.project.gomgom.post.service;

import com.project.gomgom.post.dto.PostDto;
import com.project.gomgom.post.entity.Post;

import java.util.Collection;

public interface PostService {
    Post createPost(PostDto postDto) throws ClassNotFoundException;
    Collection<PostDto> readPost(Long boardId, Long postId);
    Collection<PostDto> readAllPost();
    Collection<PostDto> readCategoryPost(Long boardId);
}
