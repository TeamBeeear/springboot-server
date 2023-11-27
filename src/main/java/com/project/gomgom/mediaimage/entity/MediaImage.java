package com.project.gomgom.mediaimage.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.gomgom.comment.entity.Comment;
import com.project.gomgom.selection.entity.Selection;
import com.project.gomgom.user.entity.User;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEDIA_IMAGE")
public class MediaImage {

    @Id @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(name = "origin_file_name", columnDefinition = "TEXT")
    private String originFileName;

    @Column(name = "file_name", columnDefinition = "TEXT")
    private String fileName;

    @Column(name = "file_path", columnDefinition = "TEXT")
    private String filePath;

    @OneToOne(mappedBy = "image")
    @JsonIgnoreProperties({"image"})
    private User user;

    @OneToOne(mappedBy = "image")
    @JsonIgnoreProperties({"image"})
    private Comment comment;

    @OneToOne(mappedBy = "image")
    @JsonIgnoreProperties({"image"})
    private Selection selection;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaImage that = (MediaImage) o;
        return Objects.equals(imageId, that.imageId) && Objects.equals(originFileName, that.originFileName) && Objects.equals(fileName, that.fileName) && Objects.equals(filePath, that.filePath) && Objects.equals(user, that.user) && Objects.equals(comment, that.comment) && Objects.equals(selection, that.selection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, originFileName, fileName, filePath, user, comment, selection);
    }

    @Override
    public String toString() {
        return "MediaImage{" +
                "imageId=" + imageId +
                ", originFileName='" + originFileName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", user=" + user +
                ", comment=" + comment +
                ", selection=" + selection +
                '}';
    }

}