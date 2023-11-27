package com.project.gomgom.vote.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.gomgom.selection.entity.Selection;
import com.project.gomgom.user.entity.User;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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