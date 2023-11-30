package com.project.gomgom.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.gomgom.gomgompost.entity.GomgomPost;
import com.project.gomgom.mediaimage.entity.MediaImage;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.user.entity.User;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "COMMENT")
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @ApiModelProperty(notes = "댓글 기본키", example = "1")
    @Id @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ApiModelProperty(notes = "사용자 정보")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gomgom_post_id")
    private GomgomPost gomgomPost;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private MediaImage image;

    @Column(name = "content", columnDefinition = "TEXT")
    @ApiModelProperty(notes = "댓글 내용", example = "댓글을 작성하는 예시입니다.")
    private String content;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @ApiModelProperty(example = "2023-10-26 17:01:31.069860")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(commentId, comment.commentId) && Objects.equals(user, comment.user) && Objects.equals(post, comment.post) && Objects.equals(gomgomPost, comment.gomgomPost) && Objects.equals(image, comment.image) && Objects.equals(content, comment.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, user, post, gomgomPost, image, content);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", user=" + user +
                ", post=" + post +
                ", gomgomPost=" + gomgomPost +
                ", image=" + image +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

}