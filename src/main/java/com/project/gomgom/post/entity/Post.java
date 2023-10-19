package com.project.gomgom.post.entity;

import com.project.gomgom.board.entity.Board;
import com.project.gomgom.comment.entity.Comment;
import com.project.gomgom.heart.entity.Heart;
import com.project.gomgom.selection.entity.Selection;
import com.project.gomgom.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "POST")
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first_selection_id")
    private Selection firstSelection;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_selection_id")
    private Selection secondSelection;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "total_vote")
    private Long totalVote;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Heart> hearts = new ArrayList<>();

}