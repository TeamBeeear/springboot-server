package com.project.gomgom.gomgompost.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GOMGOM_POST")
@EntityListeners(AuditingEntityListener.class)
public class GomgomPost {

    @Id @Column(name = "gomgom_post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gomgomPostId;

    @Column(name = "gomgom_main_image_id")
    private Long gomgomMainImageId;

    @Column(name = "gomgom_detail_image_id")
    private Long gomgomDetailImageId;

    @Column(name = "title")
    private String title;

    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
