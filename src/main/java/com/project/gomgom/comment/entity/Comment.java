package com.project.gomgom.comment.entity;

import com.project.gomgom.gomgompost.entity.GomgomPost;
import com.project.gomgom.mediaimage.entity.MediaImage;
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
@Table(name = "COMMENT")
public class Comment {

    @Id @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gomgom_post_id")
    private GomgomPost gomgomPost;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private MediaImage image;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

}