package org.example.springbootmeilisearch.domain.post.postDocument.service;

import org.example.springbootmeilisearch.domain.post.postdocument.document.PostDocument;
import org.example.springbootmeilisearch.domain.post.postdocument.service.PostDocumentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class PostDocumentServiceTest {
    @Autowired
    private PostDocumentService postDocumentService;

    @Test
    @DisplayName("findAll")
    void t1() {
        List<PostDocument> posts = postDocumentService.findAll();

        assertThat(posts).hasSize(3);

        assertPost(posts.get(0), 3L, "subject3", "body3");
        assertPost(posts.get(1), 2L, "subject2", "body2");
        assertPost(posts.get(2), 1L, "subject1", "body1");
    }

    @Test
    @DisplayName("findById")
    void t2() {
        PostDocument post = postDocumentService.findById(1).get();

        assertThat(post).isNotNull();

        assertPost(post, 1L, "subject1", "body1");
    }

    private void assertPost(PostDocument post, Long expectedId, String expectedSubject, String expectedBody) {
        assertThat(post.getId()).isEqualTo(expectedId);
        assertThat(post.getSubject()).isEqualTo(expectedSubject);
        assertThat(post.getBody()).isEqualTo(expectedBody);
    }
}
