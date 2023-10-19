package com.project.gomgom.user.entity;

import com.project.gomgom.comment.entity.Comment;
import com.project.gomgom.heart.entity.Heart;
import com.project.gomgom.mediaimage.entity.MediaImage;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.vote.entity.Vote;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
public class User {

    @Id @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pw", nullable = false)
    private String userPw;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private MediaImage image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Heart> hearts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();

}