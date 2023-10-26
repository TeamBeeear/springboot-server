package com.project.gomgom.selection.repository;

import com.project.gomgom.selection.entity.Selection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectionRepository extends JpaRepository<Selection, Long> {
}
