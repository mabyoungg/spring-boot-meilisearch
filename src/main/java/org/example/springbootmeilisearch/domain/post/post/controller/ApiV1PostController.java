package org.example.springbootmeilisearch.domain.post.post.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootmeilisearch.domain.post.post.service.PostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ap1/v1/posts")
@RequiredArgsConstructor
public class ApiV1PostController {
    private final PostService postService;
}
