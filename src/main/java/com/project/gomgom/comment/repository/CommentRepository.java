package com.project.gomgom.comment.repository;

import com.project.gomgom.comment.entity.Comment;
import com.project.gomgom.heart.entity.Heart;
import com.project.gomgom.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUser(User user);
}
