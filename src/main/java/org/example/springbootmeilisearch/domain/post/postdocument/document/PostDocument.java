package org.example.springbootmeilisearch.domain.post.postdocument.document;

import lombok.Getter;
import org.example.springbootmeilisearch.domain.post.post.dto.PostDto;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Getter
public class PostDocument {
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

    public PostDocument(PostDto postDto) {
        this.id = postDto.getId();
        this.createDate = postDto.getCreateDate();
        this.modifyDate = postDto.getModifyDate();
        this.subject = postDto.getSubject();
        this.body = postDto.getBody();
    }
}
