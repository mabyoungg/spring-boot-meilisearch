package org.example.springbootmeilisearch.domain.post.post.eventListener;

import lombok.RequiredArgsConstructor;
import org.example.springbootmeilisearch.domain.post.post.event.AfterPostCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostEventListener {
    @EventListener
    @Async
    public void listen(AfterPostCreatedEvent event) {
        System.out.println("event = " + event.getPost());
    }
}
