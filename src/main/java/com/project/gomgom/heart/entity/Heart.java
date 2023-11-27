package com.project.gomgom.heart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.user.entity.User;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
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