package com.project.gomgom.selection.entity;

import com.project.gomgom.mediaimage.entity.MediaImage;
import com.project.gomgom.vote.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SELECTION")
public class Selection {

    @Id @Column(name = "selection_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long selectionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private MediaImage image;

    @Column(name = "content")
    private String content;

    @Column(name = "vote_count")
    private Long voteCount;

    @Column(name = "vote_percentage")
    private String votePercentage;

    @OneToMany(mappedBy = "selection", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();

}