package com.project.gomgom.gomgompost.entity;

import com.project.gomgom.comment.entity.Comment;
import com.project.gomgom.mediaimage.entity.MediaImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gomgom_main_image_id")
    private MediaImage gomgomMainImage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gomgom_detail_image_id")
    private MediaImage gomgomDetailImage;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "gomgomPost", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

}