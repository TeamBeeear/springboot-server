package com.project.gomgom.selection.entity;

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
@Table(name = "SELECTION")
public class Selection {

    @Id @Column(name = "selection_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "content")
    private String content;

    @Column(name = "vote_count")
    private Long voteCount;

    @Column(name = "vote_percentage")
    private String votePercentage;

}
