package com.project.gomgom.heart.repository;

import com.project.gomgom.heart.entity.Heart;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByUserAndPost(User user, Post post);
    List<Heart> findByUser(User user);
}
