package com.project.gomgom.heart.entity;

import com.project.gomgom.post.entity.Post;
import com.project.gomgom.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}