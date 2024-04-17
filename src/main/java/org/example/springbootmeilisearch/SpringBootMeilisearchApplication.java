package org.example.springbootmeilisearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootMeilisearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMeilisearchApplication.class, args);
    }

}
