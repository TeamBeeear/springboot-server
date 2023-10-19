package com.project.gomgom.vote.entity;

import com.project.gomgom.selection.entity.Selection;
import com.project.gomgom.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VOTE")
public class Vote {

    @Id @Column(name = "vote_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "selection_id")
    private Selection selection;

}