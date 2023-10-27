package com.project.gomgom.post.service;

import com.project.gomgom.board.repository.BoardRepository;
import com.project.gomgom.post.dto.AllCategoryResDto;
import com.project.gomgom.post.dto.OneCategoryResDto;
import com.project.gomgom.post.dto.OnePostResDto;
import com.project.gomgom.post.dto.PostDto;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.post.repository.PostRepository;
import com.project.gomgom.selection.entity.Selection;
import com.project.gomgom.selection.repository.SelectionRepository;
import com.project.gomgom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final SelectionRepository selectionRepository;

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

        // selection 고려
        if (postDto.getFirstSelectionContent().equals("")) {
            throw new EmptyResultDataAccessException(0);
        }

        // selection 저장
        Selection newSelection1 = Selection.builder()
                .voteCount(0L)
                .votePercentage("0%")
                .content(postDto.getFirstSelectionContent())
                .build();

        Selection newSelection2 = Selection.builder()
                .voteCount(0L)
                .votePercentage("0%")
                .content(postDto.getSecondSelectionContent())
                .build();

        Selection firstSelection = selectionRepository.save(newSelection1);
        Selection secondSelection = selectionRepository.save(newSelection2);

        // 포스트 생성
        Post newPost = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .totalVote(0L)
                .user(userRepository.findById(postDto.getUserId()).get())
                .board(boardRepository.findById(postDto.getBoardId()).get())
                .firstSelection(selectionRepository.findById(firstSelection.getSelectionId()).get())
                .secondSelection(selectionRepository.findById(secondSelection.getSelectionId()).get())
                .build();

        postRepository.save(newPost);

        // 추후에 selection image 도 고려
        
         return newPost;
    }

    @Override
    public OnePostResDto readPost(Long boardId, Long postId) {

        // boardId 검증
        if (!boardRepository.findById(boardId).isPresent()) {
            throw new NoResultException("게시판이 존재하지 않습니다.");
        }

        // postId 검증
        if (!postRepository.findById(postId).isPresent()) {
            throw new NoSuchElementException("게시글이 존재하지 않습니다.");
        }

        // post 찾기
        Post gotPost = postRepository.findById(postId).get();

        // ** 추후에 댓글 개수, 좋아요 개수 추가하기 **
        return OnePostResDto.builder()
                .postId(postId)
                .userId(gotPost.getUser().getUserId())
                .title(gotPost.getTitle())
                .content(gotPost.getContent())
                .firstSelectionId(gotPost.getFirstSelection().getSelectionId())
                .firstSelectionContent(gotPost.getFirstSelection().getContent())
                .firstSelectionVotePercentage(gotPost.getFirstSelection().getVotePercentage())
                .secondSelectionId(gotPost.getSecondSelection().getSelectionId())
                .secondSelectionContent(gotPost.getSecondSelection().getContent())
                .secondSelectionVotePercentage(gotPost.getSecondSelection().getVotePercentage())
                .comments(gotPost.getComments())
                .build();
    }

    @Override
    public Collection<OneCategoryResDto> readCategoryPost(Long boardId) {

        // boardId 검증
        if(!boardRepository.findById(boardId).isPresent()) {
            throw new NoResultException("게시판이 존재하지 않습니다.");
        }

        return postRepository.findPostsByBoardId(boardId);

    }

    @Override
    public Collection<AllCategoryResDto> readAllPost() {

        List<Post> posts = postRepository.findAll();
        List<AllCategoryResDto> result = posts.stream()
                .map(post -> AllCategoryResDto.builder()
                        .postId(post.getPostId())
                        .boardId(post.getBoard().getBoardId())
                        .userId(post.getUser().getUserId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .firstSelectionId(post.getFirstSelection().getSelectionId())
                        .firstSelectionContent(post.getFirstSelection().getContent())
                        .firstSelectionVotePercentage(post.getFirstSelection().getVotePercentage())
                        .secondSelectionId(post.getSecondSelection().getSelectionId())
                        .secondSelectionContent(post.getSecondSelection().getContent())
                        .secondSelectionVotePercentage(post.getSecondSelection().getVotePercentage())
                        .commentsCount((long) post.getComments().size())
                        .heartsCount((long) post.getHearts().size())
                        .build())
                .collect(Collectors.toList());

        if(result.size() == 0) {
            throw new NoResultException("작성된 글이 없습니다.");
        }

        return result;

    }
}
