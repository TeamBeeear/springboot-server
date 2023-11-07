package com.project.gomgom.post.repository;

import com.project.gomgom.post.dto.OneCategoryResDto;
import com.project.gomgom.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT new com.project.gomgom.post.dto.OneCategoryResDto(p.postId, p.board.boardId, p.user.userId, p.title, p.content, " +
            "p.firstSelection.selectionId, p.firstSelection.content, p.firstSelection.votePercentage, " +
            "p.secondSelection.selectionId, p.secondSelection.content, p.secondSelection.votePercentage, " +
            "COUNT(c), COUNT(h)) " +
            "FROM Post p LEFT JOIN p.comments c LEFT JOIN p.hearts h " +
            "WHERE p.board.boardId = :boardId " +
            "GROUP BY p.postId")
    Collection<OneCategoryResDto> findPostsByBoardId(@Param("boardId") Long boardId);

    List<Post> findAll();

}
