package pl.prawko.prawko_server.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import pl.prawko.prawko_server.dto.ApiResponse;
import pl.prawko.prawko_server.test_utils.TestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionControllerTest {

    public static final String URL = "/questions";

    @LocalServerPort
    int port;

    private RestClient restClient;

    @BeforeEach
    void setUp() {
        restClient = RestClient.builder()
                .baseUrl(TestUtils.BASE_URL + port)
                .build();
    }

    @Test
    void addQuestions_throwBadRequest_whenFileIsMissing() {
        final var expected = "Required part 'file' is not present.";

        final var response = restClient.post()
                .uri(URL)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(new LinkedMultiValueMap<>())
                .exchange((req, res) -> TestUtils.getResponseEntity(res));

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().message()).isEqualTo(expected);
    }

    @Test
    void addQuestions_throwUnsupportedMediaType_whenFileFormatIsWrong() {
        final var expected = "Invalid file format.";
        final var fileResource = new ByteArrayResource(new byte[0]) {
            @Override
            public String getFilename() {
                return "wrong-file.txt";
            }
        };

        final var parts = new LinkedMultiValueMap<String, Object>();
        parts.add("file", fileResource);

        final var response = restClient.post()
                .uri(URL)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(parts)
                .exchange((req, res) -> TestUtils.getResponseEntity(res));

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        assertThat(response.getBody().message()).isEqualTo(expected);
    }

    @Test
    void addQuestions_returnOk_whenSuccess() {
        final var expected = "Questions from file added successfully.";
        final ClassPathResource resource = new ClassPathResource("test_question.csv");
        final MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("file", resource);

        final var response = restClient.post()
                .uri(URL)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(parts)
                .retrieve()
                .toEntity(ApiResponse.class);

        Assertions.assertNotNull(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().message()).isEqualTo(expected);
    }

}
