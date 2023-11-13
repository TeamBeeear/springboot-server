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
    List<Post> findAllByBoard_BoardId(Long boardId);
    List<Post> findAll();

}
