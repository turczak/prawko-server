package pl.prawko.prawko_server.test_utils;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class MultiPartFactory {

    private static final String PART_NAME = "file";

    public static MultiValueMap<String, Object> empty() {
        return new LinkedMultiValueMap<>();
    }

    public static MultiValueMap<String, Object> withWrongFile() {
        final var resource = new ByteArrayResource(new byte[0]) {
            @Override
            public String getFilename() {
                return "wrong-file.txt";
            }
        };
        final var parts = empty();
        parts.add(PART_NAME, resource);
        return parts;
    }

    public static MultiValueMap<String, Object> fromClasspath(final String path) {
        final var parts = empty();
        parts.add(PART_NAME, new ClassPathResource(path));
        return parts;
    }

}
