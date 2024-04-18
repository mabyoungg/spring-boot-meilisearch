package org.example.springbootmeilisearch.domain.post.postdocument.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootmeilisearch.domain.post.post.dto.PostDto;
import org.example.springbootmeilisearch.domain.post.postdocument.document.PostDocument;
import org.example.springbootmeilisearch.domain.post.postdocument.repository.PostDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostDocumentService {
    private final PostDocumentRepository postDocumentRepository;

    public void add(PostDto postDto) {
        PostDocument postDocument = new PostDocument(postDto);

        postDocumentRepository.save(postDocument);
    }

    public void clear() {
        postDocumentRepository.clear();
    }

    public List<PostDocument> findAll() {
        return postDocumentRepository.findByOrderByIdDesc();
    }
}
