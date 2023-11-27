package com.project.gomgom.selection.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.gomgom.mediaimage.entity.MediaImage;
import com.project.gomgom.vote.entity.Vote;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SELECTION")
public class Selection {

    @Id @Column(name = "selection_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long selectionId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private MediaImage image;

    @Column(name = "content")
    private String content;

    @Column(name = "vote_count")
    private Long voteCount;

    @Column(name = "vote_percentage")
    private String votePercentage;

    @JsonIgnore
    @OneToMany(mappedBy = "selection", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Selection selection = (Selection) o;
        return Objects.equals(selectionId, selection.selectionId) && Objects.equals(image, selection.image) && Objects.equals(content, selection.content) && Objects.equals(voteCount, selection.voteCount) && Objects.equals(votePercentage, selection.votePercentage) && Objects.equals(votes, selection.votes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(selectionId, image, content, voteCount, votePercentage, votes);
    }

    @Override
    public String toString() {
        return "Selection{" +
                "selectionId=" + selectionId +
                ", image=" + image +
                ", content='" + content + '\'' +
                ", voteCount=" + voteCount +
                ", votePercentage='" + votePercentage + '\'' +
                ", votes=" + votes +
                '}';
    }

}