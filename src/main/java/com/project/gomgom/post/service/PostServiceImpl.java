package com.project.gomgom.post.service;

import com.project.gomgom.board.repository.BoardRepository;
import com.project.gomgom.comment.entity.Comment;
import com.project.gomgom.comment.repository.CommentRepository;
import com.project.gomgom.heart.entity.Heart;
import com.project.gomgom.heart.repository.HeartRepository;
import com.project.gomgom.post.dto.AllCategoryResDto;
import com.project.gomgom.post.dto.MyPageResDto;
import com.project.gomgom.post.dto.OneCategoryResDto;
import com.project.gomgom.post.dto.OnePostResDto;
import com.project.gomgom.post.dto.PostDto;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.post.repository.PostRepository;
import com.project.gomgom.selection.entity.Selection;
import com.project.gomgom.selection.repository.SelectionRepository;
import com.project.gomgom.user.entity.User;
import com.project.gomgom.user.repository.UserRepository;
import com.project.gomgom.util.exception.CustomException;
import com.project.gomgom.util.exception.ErrorCode;
import com.project.gomgom.util.formatter.TimeAgoFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
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
    private final HeartRepository heartRepository;
    private final CommentRepository commentRepository;

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

        // ** 좋아요 기능 추가함 **
        return OnePostResDto.builder()
                .postId(postId)
                .boardId(boardId)
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
                .heartsCount(0L)
                .build();

    }

    @Override
    public Collection<OneCategoryResDto> readCategoryPost(Long boardId) {

        // board 가 존재하지 않는 경우
        if(!boardRepository.findById(boardId).isPresent()) {
            throw new CustomException(ErrorCode.BOARD_NOT_FOUND);
        }

        // 해당 게시판의 게시글 조회
        List<Post> postsInCategory = postRepository.findAllByBoard_BoardId(boardId);

        // 해당 카테고리에 게시글이 없는 경우 처리
        if (postsInCategory.isEmpty()) {
            throw new CustomException(ErrorCode.BOARD_EMPTY);
        }

        // DTO로 매핑하여 반환
        return postsInCategory.stream()
                .map(post -> OneCategoryResDto.builder()
                        .postId(post.getPostId())
                        .boardId(post.getBoard().getBoardId())
                        .title(post.getTitle())
                        .commentsCount((long) post.getComments().size())
                        .heartsCount((long) post.getHearts().size())
                        .firstSelectionContent(post.getFirstSelection().getContent())
                        .secondSelectionContent(post.getSecondSelection().getContent())
                        .content(post.getContent())
                        .userId(post.getUser().getUserId())
                        .nMinutesAgo(TimeAgoFormatter.format(post.getCreatedAt()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<AllCategoryResDto> readAllPost() {

        List<Post> posts = postRepository.findAll();
        List<AllCategoryResDto> result = posts.stream()
                .map(post -> AllCategoryResDto.builder()
                        .postId(post.getPostId())
                        .boardId(post.getBoard().getBoardId())
                        .title(post.getTitle())
                        .commentsCount((long) post.getComments().size())
                        .heartsCount((long) post.getHearts().size())
                        .firstSelectionContent(post.getFirstSelection().getContent())
                        .secondSelectionContent(post.getSecondSelection().getContent())
                        .content(post.getContent())
                        .userId(post.getUser().getUserId())
                        .nMinutesAgo(TimeAgoFormatter.format(post.getCreatedAt()))
                        .build())
                .collect(Collectors.toList());

        // 게시판에 작성된 글이 없는 경우
        if(result.size() == 0) {
            throw new CustomException(ErrorCode.BOARD_EMPTY);
        }

        return result;

    }

    @Override
    public Collection<MyPageResDto> readMyPage(String userId, Long category) {

        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isEmpty()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        if(!(category >= 1 && category <= 3)) {
            throw new CustomException(ErrorCode.CATEGORY_NOT_FOUND);
        }

        List<Post> writtenPosts = new ArrayList<>();
        List<Heart> heartedPosts = new ArrayList<>();
        List<Comment> commentedPosts = new ArrayList<>();

        List<MyPageResDto> result = new ArrayList<>();

        try {
            // category 가 1 이면 내가 작성한 글
            if (category == 1) {
                writtenPosts = existingUser.get().getPosts();

                // 게시글이 0 개인 경우 처리

                result = writtenPosts.stream()
                        .map(post -> MyPageResDto.builder()
                                .postId(post.getPostId())
                                .boardId(post.getBoard().getBoardId())
                                .title(post.getTitle())
                                .commentsCount((long) post.getComments().size())
                                .heartsCount((long) post.getHearts().size())
                                .firstSelectionContent(post.getFirstSelection().getContent())
                                .secondSelectionContent(post.getSecondSelection().getContent())
                                .content(post.getContent())
                                .userId(post.getUser().getUserId())
                                .nMinutesAgo(TimeAgoFormatter.format(post.getCreatedAt()))
                                .build())
                        .collect(Collectors.toList());
            }

            // category 가 2 이면 내가 공감한 글
            else if (category == 2) {
                heartedPosts = heartRepository.findByUser(existingUser.get());

                // 게시글이 0 개인 경우 처리

                for (Heart heart : heartedPosts) {
                    Post post = heart.getPost();
                    MyPageResDto myPageResDto = MyPageResDto.builder()
                            .postId(post.getPostId())
                            .boardId(post.getBoard().getBoardId())
                            .title(post.getTitle())
                            .commentsCount((long) post.getComments().size())
                            .heartsCount((long) post.getHearts().size())
                            .firstSelectionContent(post.getFirstSelection().getContent())
                            .secondSelectionContent(post.getSecondSelection().getContent())
                            .content(post.getContent())
                            .userId(post.getUser().getUserId())
                            .nMinutesAgo(TimeAgoFormatter.format(post.getCreatedAt()))
                            .build();
                    result.add(myPageResDto);
                }
            }

            // category 가 3 이면 내가 댓글을 단 글
            else if (category == 3) {
                commentedPosts = commentRepository.findByUser(existingUser.get());

                // 게시글이 0 개인 경우 처리

                for (Comment comment : commentedPosts) {
                    Post post = comment.getPost();
                    MyPageResDto myPageResDto = MyPageResDto.builder()
                            .postId(post.getPostId())
                            .boardId(post.getBoard().getBoardId())
                            .title(post.getTitle())
                            .commentsCount((long) post.getComments().size())
                            .heartsCount((long) post.getHearts().size())
                            .firstSelectionContent(post.getFirstSelection().getContent())
                            .secondSelectionContent(post.getSecondSelection().getContent())
                            .content(post.getContent())
                            .userId(post.getUser().getUserId())
                            .nMinutesAgo(TimeAgoFormatter.format(post.getCreatedAt()))
                            .build();
                    result.add(myPageResDto);
                }

                // 게시글 중복 제거
                Set<MyPageResDto> resultSet = new HashSet<>(result);
                result.clear();
                result.addAll(resultSet);
            }

        } catch (NullPointerException e) {
            return Collections.emptyList();
        }
        return result;
    }

}