package com.project.gomgom.vote.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.gomgom.selection.entity.Selection;
import com.project.gomgom.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Objects;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "selection_id")
    private Selection selection;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return Objects.equals(voteId, vote.voteId) && Objects.equals(user, vote.user) && Objects.equals(selection, vote.selection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voteId, user, selection);
    }

    @Override
    public String toString() {
        return "Vote{" +
                "voteId=" + voteId +
                ", user=" + user +
                ", selection=" + selection +
                '}';
    }

}