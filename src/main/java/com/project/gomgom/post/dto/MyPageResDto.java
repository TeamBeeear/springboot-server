package com.project.gomgom.post.dto;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class MyPageResDto {

    private Long postId;
    private Long boardId;
    private String title;
    private Long commentsCount;
    private Long heartsCount;
    private String firstSelectionContent;
    private String secondSelectionContent;
    private String content;
    private String userId;
    private String nMinutesAgo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPageResDto that = (MyPageResDto) o;
        return Objects.equals(postId, that.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }

}
