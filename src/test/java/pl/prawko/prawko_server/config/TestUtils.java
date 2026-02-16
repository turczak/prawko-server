package pl.prawko.prawko_server.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import pl.prawko.prawko_server.dto.ApiResponse;

import java.io.IOException;

public class TestUtils {

    public static final String BASE_URL = "http://localhost:";

    public static ResponseEntity<ApiResponse> getResponseEntity(final RestClient.RequestHeadersSpec.ConvertibleClientHttpResponse response) throws IOException {
        final var status = HttpStatus.valueOf(response.getStatusCode().value());
        final var headers = response.getHeaders();
        final var body = response.bodyTo(ApiResponse.class);
        return new ResponseEntity<>(body, headers, status);
    }

    public static void authUser(final HttpHeaders headers) {
        headers.setBasicAuth("pippin", "lembasy");
    }

    public static void authAdmin(final HttpHeaders headers) {
        headers.setBasicAuth("gimli", "krasnoludka");
    }

}
