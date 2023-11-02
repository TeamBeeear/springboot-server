package com.project.gomgom.gomgompost.repository;

import com.project.gomgom.gomgompost.entity.GomgomPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GomgomPostRepository extends JpaRepository<GomgomPost, Long> {
}
