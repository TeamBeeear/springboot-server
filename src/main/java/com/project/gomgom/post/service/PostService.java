package com.project.gomgom.post.service;

import com.project.gomgom.post.dto.AllCategoryResDto;
import com.project.gomgom.post.dto.OneCategoryResDto;
import com.project.gomgom.post.dto.OnePostResDto;
import com.project.gomgom.post.dto.PostDto;
import com.project.gomgom.post.entity.Post;

import java.util.Collection;

public interface PostService {
    Post createPost(PostDto postDto) throws ClassNotFoundException;
    OnePostResDto readPost(Long boardId, Long postId);
    Collection<OneCategoryResDto> readCategoryPost(Long boardId);
    Collection<AllCategoryResDto> readAllPost();
}
