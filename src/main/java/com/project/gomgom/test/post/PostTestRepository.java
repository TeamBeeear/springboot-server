package com.project.gomgom.test.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTestRepository extends JpaRepository<PostTestEntity, Long> {
    PostTestEntity findPostTestEntityById(Long id);
}
