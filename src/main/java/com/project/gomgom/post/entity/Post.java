package com.project.gomgom.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "POST")
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example = "1")
    private Long postId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @ApiModelProperty(example = "1")
    private Board board;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first_selection_id")
    private Selection firstSelection;

    @JsonIgnore
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

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Heart> hearts = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(postId, post.postId) && Objects.equals(board, post.board) && Objects.equals(user, post.user) && Objects.equals(firstSelection, post.firstSelection) && Objects.equals(secondSelection, post.secondSelection) && Objects.equals(title, post.title) && Objects.equals(content, post.content) && Objects.equals(totalVote, post.totalVote) && Objects.equals(createdAt, post.createdAt) && Objects.equals(comments, post.comments) && Objects.equals(hearts, post.hearts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, board, user, firstSelection, secondSelection, title, content, totalVote, createdAt, comments, hearts);
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", board=" + board +
                ", user=" + user +
                ", firstSelection=" + firstSelection +
                ", secondSelection=" + secondSelection +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", totalVote=" + totalVote +
                ", createdAt=" + createdAt +
                ", comments=" + comments +
                ", hearts=" + hearts +
                '}';
    }

}