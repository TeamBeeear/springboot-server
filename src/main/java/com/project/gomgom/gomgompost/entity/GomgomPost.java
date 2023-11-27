package com.project.gomgom.gomgompost.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.gomgom.comment.entity.Comment;
import com.project.gomgom.mediaimage.entity.MediaImage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GOMGOM_POST")
@EntityListeners(AuditingEntityListener.class)
public class GomgomPost {

    @Id @Column(name = "gomgom_post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gomgomPostId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gomgom_main_image_id")
    private MediaImage gomgomMainImage;

    @JsonIgnore
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

    @JsonIgnore
    @OneToMany(mappedBy = "gomgomPost", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GomgomPost that = (GomgomPost) o;
        return Objects.equals(gomgomPostId, that.gomgomPostId) && Objects.equals(gomgomMainImage, that.gomgomMainImage) && Objects.equals(gomgomDetailImage, that.gomgomDetailImage) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(createdAt, that.createdAt) && Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gomgomPostId, gomgomMainImage, gomgomDetailImage, title, content, createdAt, comments);
    }

    @Override
    public String toString() {
        return "GomgomPost{" +
                "gomgomPostId=" + gomgomPostId +
                ", gomgomMainImage=" + gomgomMainImage +
                ", gomgomDetailImage=" + gomgomDetailImage +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", comments=" + comments +
                '}';
    }

}