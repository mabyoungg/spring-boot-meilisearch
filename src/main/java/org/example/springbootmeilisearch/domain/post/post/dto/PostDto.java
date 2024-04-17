package org.example.springbootmeilisearch.domain.post.post.dto;

import lombok.Getter;
import org.example.springbootmeilisearch.domain.post.post.entity.Post;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Getter
public class PostDto {
    @NonNull
    private long id;
    @NonNull
    private LocalDateTime createDate;
    @NonNull
    private LocalDateTime modifyDate;
    @NonNull
    private String subject;
    @NonNull
    private String body;

    public PostDto(Post post) {
        this.id = post.getId();
        this.createDate = post.getCreateDate();
        this.modifyDate = post.getModifyDate();
        this.subject = post.getSubject();
        this.body = post.getBody();
    }
}
