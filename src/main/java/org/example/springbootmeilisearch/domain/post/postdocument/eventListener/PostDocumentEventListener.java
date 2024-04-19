package org.example.springbootmeilisearch.domain.post.postdocument.eventListener;

import lombok.RequiredArgsConstructor;
import org.example.springbootmeilisearch.domain.post.post.dto.PostDto;
import org.example.springbootmeilisearch.domain.post.post.event.AfterPostCreatedEvent;
import org.example.springbootmeilisearch.domain.post.post.event.AfterPostModifiedEvent;
import org.example.springbootmeilisearch.domain.post.postdocument.service.PostDocumentService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostDocumentEventListener {
    private final PostDocumentService postDocumentService;

    @EventListener
    @Async
    public void listen(AfterPostCreatedEvent event) {
        PostDto postDto = event.getPostDto();

        postDocumentService.add(postDto);
    }

    @EventListener
    @Async
    public void listen(AfterPostModifiedEvent event) {
        PostDto postDto = event.getPostDto();

        postDocumentService.modify(postDto);
    }
}
