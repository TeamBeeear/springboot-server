package com.project.gomgom.comment.entity;

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

    @Column(name = "user_id")
    private String userId;

    @Column(name = "post_id")
    private String postId;

    @Column(name = "comment_image_id")
    private Long commentImageId;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

}