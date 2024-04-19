package org.example.springbootmeilisearch.standard.util;

import lombok.SneakyThrows;
import org.example.springbootmeilisearch.global.app.AppConfig;

import java.time.LocalDateTime;
import java.util.Map;

public class Ut {
    public static class time {
        public static long toTimeStamp(LocalDateTime localDateTime) {
            return localDateTime.toEpochSecond(java.time.ZoneOffset.ofHours(9));
        }
    }

    public static class json {
        @SneakyThrows
        public static String toString(Object obj) {
            return AppConfig.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        }

        @SneakyThrows
        public static <T> T toObject(String jsonStr, Class<T> cls) {
            return AppConfig.getObjectMapper().readValue(jsonStr, cls);
        }

        @SneakyThrows
        public static <T> T toObject(Map<String, Object> map, Class<T> cls) {
            return AppConfig.getObjectMapper().convertValue(map, cls);
        }
    }
}
