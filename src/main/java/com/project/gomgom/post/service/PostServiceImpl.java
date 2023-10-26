package com.project.gomgom.post.service;

import com.project.gomgom.board.repository.BoardRepository;
import com.project.gomgom.post.dto.PostDto;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.post.repository.PostRepository;
import com.project.gomgom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Override
    public Post createPost(PostDto postDto) throws ClassNotFoundException {

        // board 연관관계 고려
        if (!boardRepository.findById(postDto.getBoardId()).isPresent()) {
            throw new NoSuchElementException("게시판이 존재하지 않습니다.");
        }

        // user 연관관계 고려
        if (!userRepository.findById(postDto.getUserId()).isPresent()) {
            throw new ClassNotFoundException("해당 유저가 존재하지 않습니다.");
        }

        // selection 저장
        // first_selection_id
        // second_selection_id

        // selectionRepository.save(newSelection1);
        // selectionRepository.save(newSelection2);

        // 포스트 생성
        Post newPost = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .totalVote(0L)
                .user(userRepository.findById(postDto.getUserId()).get())
                .board(boardRepository.findById(postDto.getBoardId()).get())
                .build();

        postRepository.save(newPost);

        // 추후에 selection image 도 고려
        
         return newPost;
    }

    @Override
    public Collection<PostDto> readPost(Long boardId, Long postId) {

        // boardId 검증

        // postId 검증

        return null;
    }

    @Override
    public Collection<PostDto> readAllPost() {
        return null;
    }

    @Override
    public Collection<PostDto> readCategoryPost(Long boardId) {
        // boardId 검증

        return null;
    }
}
