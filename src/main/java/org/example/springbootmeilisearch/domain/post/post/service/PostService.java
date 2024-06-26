package org.example.springbootmeilisearch.domain.post.post.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootmeilisearch.domain.post.post.dto.PostDto;
import org.example.springbootmeilisearch.domain.post.post.entity.Post;
import org.example.springbootmeilisearch.domain.post.post.repository.PostRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
//    private final ApplicationEventPublisher publisher;
    private final KafkaTemplate<Object, Object> template;

    @Transactional
    public Post write(String subject, String body) {
        Post post = postRepository.save(
                Post.builder()
                        .subject(subject)
                        .body(body)
                        .build());

//        publisher.publishEvent(new AfterPostCreatedEvent(this,new PostDto(post)));
        template.send("AfterPostCreatedEvent", new PostDto(post));

        return post;
    }

    public long count() {
        return postRepository.count();
    }

    public List<Post> findAll() {
        return postRepository.findByOrderByIdDesc();
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    public void modified(Post post) {
//        publisher.publishEvent(new AfterPostModifiedEvent(this, new PostDto(post)));
        template.send("AfterPostModifiedEvent", new PostDto(post));
    }
}
