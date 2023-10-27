package com.project.gomgom.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.gomgom.gomgompost.entity.GomgomPost;
import com.project.gomgom.mediaimage.entity.MediaImage;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COMMENT")
public class Comment {

    @Id @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

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
    private String content;

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
                '}';
    }
}