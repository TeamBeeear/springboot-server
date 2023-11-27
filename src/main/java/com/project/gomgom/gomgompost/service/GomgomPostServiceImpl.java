package com.project.gomgom.gomgompost.service;

import com.project.gomgom.comment.entity.Comment;
import com.project.gomgom.gomgompost.dto.GomgomPostAllResDto;
import com.project.gomgom.gomgompost.dto.GomgomPostOneResDto;
import com.project.gomgom.gomgompost.dto.GomgomPostReqDto;
import com.project.gomgom.gomgompost.entity.GomgomPost;
import com.project.gomgom.gomgompost.repository.GomgomPostRepository;
import com.project.gomgom.util.exception.CustomException;
import com.project.gomgom.util.exception.ErrorCode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GomgomPostServiceImpl implements GomgomPostService {

    private final GomgomPostRepository gomgomPostRepository;

    @Override
    public void createPost(GomgomPostReqDto dto) {
        GomgomPost newPost = GomgomPost.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                        .build();
        gomgomPostRepository.save(newPost);
    }

    @Override
    public Collection<GomgomPostAllResDto> readAllPost() {

        List<GomgomPost> gomgomPosts = gomgomPostRepository.findAll();
        List<GomgomPostAllResDto> result = new ArrayList<>();

        for (GomgomPost post : gomgomPosts) {
            GomgomPostAllResDto postDto = new GomgomPostAllResDto();
            postDto.setId(post.getGomgomPostId());
            postDto.setTitle(post.getTitle());
            result.add(postDto);
        }

        return result;
    }

    @Override
    public GomgomPostOneResDto readOnePost(Long id) {

        Optional<GomgomPost> existingPost = gomgomPostRepository.findById(id);

        if(!existingPost.isPresent()) {
            throw new CustomException(ErrorCode.GOMGOM_POST_NOT_FOUND);
        }

        GomgomPost post = existingPost.get();

        List<Comment> comments = post.getComments();
        if (comments == null) {
            comments = new ArrayList<>(); // 댓글이 없는 경우 빈 리스트 생성
        }

        GomgomPostOneResDto result = GomgomPostOneResDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .comments(comments)
                .build();

        return result;
    }

}