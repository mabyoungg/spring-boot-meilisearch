package org.example.springbootmeilisearch.domain.post.postdocument.repository;

import com.meilisearch.sdk.Index;
import lombok.RequiredArgsConstructor;
import org.example.springbootmeilisearch.domain.post.postdocument.document.PostDocument;
import org.example.springbootmeilisearch.global.meilisearch.MeilisearchConfig;
import org.example.springbootmeilisearch.standard.util.Ut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostDocumentRepository {
    private final MeilisearchConfig meilisearchConfig;
    private Index postIndex;

    private Index getIndex() {
        if (postIndex == null) postIndex = meilisearchConfig.meilisearchClient().index("post");

        return postIndex;
    }

    public void save(PostDocument postDocument) {
        getIndex().addDocuments(
                Ut.json.toString(postDocument)
        );
    }

    public void clear() {
        getIndex().deleteAllDocuments();
    }
}
