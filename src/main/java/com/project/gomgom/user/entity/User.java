package com.project.gomgom.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.gomgom.comment.entity.Comment;
import com.project.gomgom.heart.entity.Heart;
import com.project.gomgom.mediaimage.entity.MediaImage;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.vote.entity.Vote;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
public class User {

    @Id @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pw", nullable = false)
    private String userPw;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private MediaImage image;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Heart> hearts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(userPw, user.userPw) && Objects.equals(image, user.image) && Objects.equals(posts, user.posts) && Objects.equals(comments, user.comments) && Objects.equals(hearts, user.hearts) && Objects.equals(votes, user.votes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userPw, image, posts, comments, hearts, votes);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                ", image=" + image +
                ", posts=" + posts +
                ", comments=" + comments +
                ", hearts=" + hearts +
                ", votes=" + votes +
                '}';
    }

}