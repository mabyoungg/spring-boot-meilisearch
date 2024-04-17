package org.example.springbootmeilisearch.domain.post.post.repository;

import org.example.springbootmeilisearch.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}