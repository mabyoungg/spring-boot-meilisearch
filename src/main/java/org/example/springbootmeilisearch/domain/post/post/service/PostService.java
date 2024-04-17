package org.example.springbootmeilisearch.domain.post.post.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootmeilisearch.domain.post.post.entity.Post;
import org.example.springbootmeilisearch.domain.post.post.event.AfterPostCreatedEvent;
import org.example.springbootmeilisearch.domain.post.post.repository.PostRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public Post write(String subject, String body) {
        Post post = postRepository.save(
                Post.builder()
                        .subject(subject)
                        .body(body)
                        .build());

        publisher.publishEvent(new AfterPostCreatedEvent(this, post));

        return post;
    }

    public long count() {
        return postRepository.count();
    }
}
