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
import com.project.gomgom.util.exception.CustomException;
import com.project.gomgom.util.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final SelectionRepository selectionRepository;

    @Override
    public Post createPost(PostDto postDto) {

        // board 가 존재하지 않는 경우
        if (!boardRepository.findById(postDto.getBoardId()).isPresent()) {
            throw new CustomException(ErrorCode.BOARD_NOT_FOUND);
        }

        // user 가 존재하지 않는 경우
        if (!userRepository.findById(postDto.getUserId()).isPresent()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        // selection 에 값이 없는 경우
        if (postDto.getFirstSelectionContent() == null
                || postDto.getFirstSelectionContent().isEmpty()
                || postDto.getFirstSelectionContent().isBlank()) {
            throw new CustomException(ErrorCode.BAD_FIRST_SELECTION);
        }

        if (postDto.getSecondSelectionContent() == null
                || postDto.getSecondSelectionContent().isEmpty()
                || postDto.getSecondSelectionContent().isBlank()) {
            throw new CustomException(ErrorCode.BAD_SECOND_SELECTION);
        }

        // post title 이 없는 경우
        if(postDto.getTitle() == null || postDto.getTitle().isEmpty() || postDto.getTitle().isBlank()) {
            throw new CustomException(ErrorCode.BAD_POST_TITLE);
        }

        // post content 가 없는 경우
        if (postDto.getContent() == null || postDto.getContent().isEmpty() || postDto.getContent().isBlank()) {
            throw new CustomException(ErrorCode.BAD_POST_CONTENT);
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

        // post 생성
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

        // board 가 존재하지 않는 경우
        if (!boardRepository.findById(boardId).isPresent()) {
            throw new CustomException(ErrorCode.BOARD_NOT_FOUND);
        }

        // post 가 존재하지 않는 경우
        if (!postRepository.findById(postId).isPresent()) {
            throw new CustomException(ErrorCode.POST_NOT_FOUND);
        }

        // post 찾기
        Post gotPost = postRepository.findById(postId).get();

        // post 가 board 에 속해있는지 검증
        if (gotPost.getBoard().getBoardId() != boardId) {
            throw new CustomException(ErrorCode.NOT_MATCH_POST_BOARD);
        }

        // ** 추후에 좋아요 개수 추가하기 **
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
                .commentsCount(gotPost.getComments().stream().count())
                .build();

    }

    @Override
    public Collection<OneCategoryResDto> readCategoryPost(Long boardId) {

        // board 가 존재하지 않는 경우
        if(!boardRepository.findById(boardId).isPresent()) {
            throw new CustomException(ErrorCode.BOARD_NOT_FOUND);
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

        // 게시판에 작성된 글이 없는 경우
        if(result.size() == 0) {
            throw new CustomException(ErrorCode.BOARD_EMPTY);
        }

        return result;

    }

}