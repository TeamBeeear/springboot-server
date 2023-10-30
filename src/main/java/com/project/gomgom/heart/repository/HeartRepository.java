package com.project.gomgom.heart.repository;

import com.project.gomgom.heart.entity.Heart;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByUserAndPost(User user, Post post);
}
