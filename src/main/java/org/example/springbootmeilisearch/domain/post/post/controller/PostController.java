package org.example.springbootmeilisearch.domain.post.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.model.SearchResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private Client client = new Client(new Config("http://localhost:7700", "masterKey"));

    @Data
    @AllArgsConstructor
    public static class Movie {
        private String id;
        private String title;
        private String[] genres;
    }

    @SneakyThrows
    @GetMapping("/makeSearchData")
    @ResponseBody
    public String makeSearchData() {

        ObjectMapper objectMapper = new ObjectMapper();

        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("1", "Carol", new String[]{"Romance", "Drama"}));
        movies.add(new Movie("2", "Wonder Woman", new String[]{"Action", "Adventure"}));
        movies.add(new Movie("3", "Life of Pi", new String[]{"Adventure", "Drama"}));
        movies.add(new Movie("4", "Mad Max: Fury Road", new String[]{"Adventure", "Science Fiction"}));
        movies.add(new Movie("5", "Moana", new String[]{"Fantasy", "Action"}));
        movies.add(new Movie("6", "Philadelphia", new String[]{"Drama"}));

        String documents = objectMapper.writeValueAsString(movies);

        Index index = client.index("movies");

        index.addDocuments(documents); // => { "taskUid": 0 }

        return "성공";
    }

    @GetMapping("/search")
    @ResponseBody
    public SearchResult search(String kw) {
        Index index = client.index("movies");
        SearchResult results = index.search(kw);

        return results;
    }
}
