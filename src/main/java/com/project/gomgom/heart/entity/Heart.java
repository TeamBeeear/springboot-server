package com.project.gomgom.heart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HEART")
public class Heart {

    @Id @Column(name = "heart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heartId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Heart heart = (Heart) o;
        return Objects.equals(heartId, heart.heartId) && Objects.equals(user, heart.user) && Objects.equals(post, heart.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(heartId, user, post);
    }

    @Override
    public String toString() {
        return "Heart{" +
                "heartId=" + heartId +
                ", user=" + user +
                ", post=" + post +
                '}';
    }

}