package com.project.gomgom.post.entity;

import com.project.gomgom.board.entity.Board;
import com.project.gomgom.comment.entity.Comment;
import com.project.gomgom.heart.entity.Heart;
import com.project.gomgom.selection.entity.Selection;
import com.project.gomgom.user.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "POST")
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example = "1")
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @ApiModelProperty(example = "1")
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
    @ApiModelProperty(example = "제목 테스트")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    @ApiModelProperty(example = "내용 테스트")
    private String content;

    @Column(name = "total_vote")
    @ApiModelProperty(example = "0")
    private Long totalVote;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @ApiModelProperty(example = "2023-10-26 17:01:31.069860")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Heart> hearts = new ArrayList<>();

}