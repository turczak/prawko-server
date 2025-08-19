package pl.prawko.prawko_server.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import pl.prawko.prawko_server.dto.ApiResponse;
import pl.prawko.prawko_server.test_utils.MultiPartFactory;
import pl.prawko.prawko_server.test_utils.TestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionControllerTest {

    public static final String URL = "/questions";

    @LocalServerPort
    private int port;

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
        final var multipart = MultiPartFactory.empty();

        final var response = restClient.post()
                .uri(URL)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(multipart)
                .exchange((req, res) -> TestUtils.getResponseEntity(res));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().message()).isEqualTo(expected);
    }

    @Test
    void addQuestions_throwUnsupportedMediaType_whenFileFormatIsWrong() {
        final var expected = "Invalid file format.";
        final var multipart = MultiPartFactory.withWrongFile();

        final var response = restClient.post()
                .uri(URL)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(multipart)
                .exchange((req, res) -> TestUtils.getResponseEntity(res));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        assertThat(response.getBody().message()).isEqualTo(expected);
    }

    @Test
    void addQuestions_returnOk_whenSuccess() {
        final var expected = "Questions from file added successfully.";
        final var multipart = MultiPartFactory.fromClasspath("test_question.csv");

        final var response = restClient.post()
                .uri(URL)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(multipart)
                .retrieve()
                .toEntity(ApiResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().message()).isEqualTo(expected);
    }

}
