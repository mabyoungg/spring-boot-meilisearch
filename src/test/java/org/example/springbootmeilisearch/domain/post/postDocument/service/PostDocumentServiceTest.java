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

        System.out.println(posts);

        assertThat(posts).hasSize(3);
    }
}
