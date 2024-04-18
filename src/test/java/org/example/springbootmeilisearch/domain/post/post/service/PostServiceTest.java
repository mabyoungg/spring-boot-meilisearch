package org.example.springbootmeilisearch.domain.post.post.service;

import org.example.springbootmeilisearch.domain.post.post.entity.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class PostServiceTest {
    @Autowired
    private PostService postService;

    @Test
    @DisplayName("findAll")
    void t1() {
        List<Post> posts = postService.findAll();

        assertThat(posts).hasSize(3);

        assertPost(posts.get(0), 3L, "subject3", "body3");
        assertPost(posts.get(1), 2L, "subject2", "body2");
        assertPost(posts.get(2), 1L, "subject1", "body1");
    }

    @Test
    @DisplayName("findById")
    void t2() {
        Post post = postService.findById(1).get();

        assertThat(post).isNotNull();

        assertPost(post, 1L, "subject1", "body1");
    }

    private void assertPost(Post post, Long expectedId, String expectedSubject, String expectedBody) {
        assertThat(post.getId()).isEqualTo(expectedId);
        assertThat(post.getSubject()).isEqualTo(expectedSubject);
        assertThat(post.getBody()).isEqualTo(expectedBody);
    }
}
