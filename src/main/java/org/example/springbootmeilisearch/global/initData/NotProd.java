package org.example.springbootmeilisearch.global.initData;

import lombok.RequiredArgsConstructor;
import org.example.springbootmeilisearch.domain.post.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@Profile("!prod")
@Configuration
@RequiredArgsConstructor
public class NotProd {
    @Autowired
    @Lazy
    private NotProd self;
    private final PostService postService;

    @Bean
    public ApplicationRunner initNotProd() {
        return args -> {
            if (postService.count() > 0) return;

            self.work1();
        };
    }

    @Transactional
    public void work1() {
        postService.write("subject1", "body1");
        postService.write("subject2", "body2");
        postService.write("subject3", "body3");
    }
}
