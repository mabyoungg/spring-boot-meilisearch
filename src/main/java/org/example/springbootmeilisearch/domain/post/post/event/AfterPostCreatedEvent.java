package org.example.springbootmeilisearch.domain.post.post.event;

import lombok.Getter;
import org.example.springbootmeilisearch.domain.post.post.entity.Post;
import org.springframework.context.ApplicationEvent;

public class AfterPostCreatedEvent extends ApplicationEvent {
    @Getter
    private final Post post;

    public AfterPostCreatedEvent(Object source, Post post) {
        super(source);
        this.post = post;
    }
}
