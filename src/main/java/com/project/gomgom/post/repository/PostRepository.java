package com.project.gomgom.post.repository;

import com.project.gomgom.post.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByBoard_BoardId(Long boardId);
    List<Post> findAll();

}
