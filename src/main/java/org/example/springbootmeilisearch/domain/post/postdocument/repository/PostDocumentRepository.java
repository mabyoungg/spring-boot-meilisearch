package org.example.springbootmeilisearch.domain.post.postdocument.repository;

import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.SearchRequest;
import com.meilisearch.sdk.model.Searchable;
import lombok.RequiredArgsConstructor;
import org.example.springbootmeilisearch.domain.post.postdocument.document.PostDocument;
import org.example.springbootmeilisearch.global.app.AppConfig;
import org.example.springbootmeilisearch.global.meilisearch.MeilisearchConfig;
import org.example.springbootmeilisearch.standard.util.Ut;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostDocumentRepository {
    private final MeilisearchConfig meilisearchConfig;
    private Index postIndex;

    private String getIndexName() {
        String indexName = "post";

        if (AppConfig.isTest()) indexName += "Test";

        return indexName;
    }

    private Index getIndex() {
        if (postIndex == null) postIndex = meilisearchConfig.meilisearchClient().index(getIndexName());

        return postIndex;
    }

    public void save(PostDocument postDocument) {
        getIndex().addDocuments(
                Ut.json.toString(postDocument)
        );
    }

    public void clear() {
        getIndex().deleteAllDocuments();
        getIndex().resetSortableAttributesSettings();
        getIndex().updateSortableAttributesSettings(new String[]{"id"});
    }

    public List<PostDocument> findByOrderByIdDesc() {
        SearchRequest searchRequest =
                new SearchRequest("")
                        .setSort(new String[]{"id:desc"});

        Searchable search = getIndex().search(searchRequest);

        return
                search
                        .getHits()
                        .stream()
                        .map(
                                hit -> Ut.json.toObject(hit, PostDocument.class)
                        )
                        .toList();
    }
}
