package com.project.gomgom.board.entity;

import com.project.gomgom.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOARD")
public class Board {

    @Id @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(name = "board_name")
    private String boardName;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

}