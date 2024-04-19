package org.example.springbootmeilisearch.domain.post.postdocument.document;

import lombok.*;
import org.example.springbootmeilisearch.domain.post.post.dto.PostDto;
import org.example.springbootmeilisearch.standard.util.Ut;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDocument {
    @NonNull
    private long id;
    @NonNull
    private LocalDateTime createDate;
    @NonNull
    private long createTimeStamp;
    @NonNull
    private LocalDateTime modifyDate;
    @NonNull
    private long modifyTimeStamp;
    @NonNull
    private String subject;
    @NonNull
    private String body;

    public PostDocument(PostDto postDto) {
        this.id = postDto.getId();
        this.createDate = postDto.getCreateDate();
        this.createTimeStamp = Ut.time.toTimeStamp(postDto.getCreateDate());
        this.modifyDate = postDto.getModifyDate();
        this.modifyTimeStamp = Ut.time.toTimeStamp(postDto.getModifyDate());
        this.subject = postDto.getSubject();
        this.body = postDto.getBody();
    }
}
