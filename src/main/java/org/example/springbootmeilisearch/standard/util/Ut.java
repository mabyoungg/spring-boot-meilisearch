package org.example.springbootmeilisearch.standard.util;

import lombok.SneakyThrows;
import org.example.springbootmeilisearch.global.app.AppConfig;

public class Ut {
    public static class json {
        @SneakyThrows
        public static String toString(Object obj) {
            return AppConfig.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        }
    }
}
