package com.project.gomgom.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTestRepository extends JpaRepository<PostTestEntity, Long> {
    PostTestEntity findPostTestEntityById(Long id);
}
