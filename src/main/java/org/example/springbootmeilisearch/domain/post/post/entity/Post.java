package org.example.springbootmeilisearch.domain.post.post.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.example.springbootmeilisearch.global.jpa.entity.BaseTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Setter
@ToString(callSuper = true)
public class Post extends BaseTime {
    private String subject;
    private String body;
}
