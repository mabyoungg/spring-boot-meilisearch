package org.example.springbootmeilisearch.domain.post.post.event;

import lombok.Getter;
import org.example.springbootmeilisearch.domain.post.post.dto.PostDto;
import org.springframework.context.ApplicationEvent;

public class AfterPostCreatedEvent extends ApplicationEvent {
    @Getter
    private final PostDto postDto;

    public AfterPostCreatedEvent(Object source, PostDto postDto) {
        super(source);
        this.postDto = postDto;
    }
}
