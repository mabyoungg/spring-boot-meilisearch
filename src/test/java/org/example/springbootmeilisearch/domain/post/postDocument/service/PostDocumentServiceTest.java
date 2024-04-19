package org.example.springbootmeilisearch.domain.post.postDocument.service;

import org.example.springbootmeilisearch.domain.post.postdocument.document.PostDocument;
import org.example.springbootmeilisearch.domain.post.postdocument.service.PostDocumentService;
import org.example.springbootmeilisearch.global.app.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
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

        assertThat(posts).hasSize(17);

        assertPost(posts.get(posts.size() - 3), 3L, "subject3", "body3");
        assertPost(posts.get(posts.size() - 2), 2L, "subject2", "body2");
        assertPost(posts.get(posts.size() - 1), 1L, "subject1", "body1");
    }

    @Test
    @DisplayName("findById")
    void t2() {
        PostDocument post = postDocumentService.findById(1).get();

        assertThat(post).isNotNull();

        assertPost(post, 1L, "subject1", "body1");
    }

    @Test
    @DisplayName("findByKw")
    void t3() {
        int page = 1;
        Sort sort = Sort.by(Sort.Order.desc("rating"));
        Pageable pageable = PageRequest.of(page - 1, 1, sort);
        Page<PostDocument> postPage = postDocumentService.findByKw("카페", pageable);

        assertThat(postPage.getNumberOfElements()).isEqualTo(1);
        assertThat(postPage.getTotalPages()).isEqualTo(2);
        assertThat(postPage.getTotalElements()).isEqualTo(2);
    }

    @Test
    @DisplayName("findByKw, '주말 카페 추천' 라고 검색하면 제목이나 내용에 '주말' or '카페' or '추천' 키워드가 1개 이상 존재")
    void t4() {
        int page = 1;
        Sort sort = Sort.by(Sort.Order.desc("rating"));
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize(), sort);
        Page<PostDocument> postPage = postDocumentService.findByKw("주말 카페 추천", pageable);

        postPage
                .getContent()
                .forEach(
                        post -> assertThat(post.getSubject() + post.getBody()).containsAnyOf("주말", "카페", "추천"));
    }

    @Test
    @DisplayName("findByKw, '\"주말\" \"카페\" \"추천\"' 라고 검색하면 제목이나 내용에 '주말', '카페', '추천' 키워드가 모두 포함")
    void t5() {
        int page = 1;
        Sort sort = Sort.by(Sort.Order.desc("rating"));
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize(), sort);
        Page<PostDocument> postPage = postDocumentService.findByKw("\"주말\" \"카페\" \"추천\"", pageable);

        postPage
                .getContent()
                .forEach(
                        post -> assertThat(post.getSubject() + post.getBody()).contains("주말", "카페", "추천"));
    }

    @Test
    @DisplayName("findByKw, 2020 년에 생성된 데이터만 검색")
    void t6() {
        // 2020년 01월 01일 00시:00분:00초.00000
        LocalDateTime startDate = LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0);

        // 2020년 12월 31일 23시:59분:59초.99999 (실제로는 999,999,999 나노초를 사용)
        LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59, 999999999);

        int page = 1;
        Sort sort = Sort.by(Sort.Order.desc("rating"));
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize(), sort);
        Page<PostDocument> postPage = postDocumentService.findByKw(
                "",
                startDate,
                endDate,
                pageable
        );

        assertThat(postPage.getTotalElements()).isEqualTo(3);
    }

    private void assertPost(PostDocument post, Long expectedId, String expectedSubject, String expectedBody) {
        assertThat(post.getId()).isEqualTo(expectedId);
        assertThat(post.getSubject()).isEqualTo(expectedSubject);
        assertThat(post.getBody()).isEqualTo(expectedBody);
    }
}
