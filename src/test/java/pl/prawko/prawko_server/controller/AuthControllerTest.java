package pl.prawko.prawko_server.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;
import pl.prawko.prawko_server.dto.ApiResponse;
import pl.prawko.prawko_server.test_utils.TestSecurityConfig;
import pl.prawko.prawko_server.test_utils.UserTestData;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.prawko.prawko_server.test_utils.TestUtils.BASE_URL;
import static pl.prawko.prawko_server.test_utils.TestUtils.getResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestSecurityConfig.class)
public class AuthControllerTest {

    private static final String URL = "/auth";

    @LocalServerPort
    int port;

    private RestClient restClient;
    private String expectedMessage;

    @BeforeEach
    void setUp() {
        restClient = RestClient.builder()
                .baseUrl(BASE_URL + port)
                .build();
    }

    @Test
    void login_returnsOk_whenCredentialsValid() {
        final var request = UserTestData.VALID_LOGIN_REQUEST;
        expectedMessage = "User signed-in successfully.";
        final var response = restClient.post()
                .uri(URL)
                .body(request)
                .retrieve()
                .toEntity(ApiResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().message()).isEqualTo(expectedMessage);
    }

    @Test
    void login_returnsUnauthorized_whenCredentialsInvalid() {
        final var request = UserTestData.INVALID_LOGIN_REQUEST;
        expectedMessage = "Invalid login or password.";
        final var response = restClient.post()
                .uri(URL)
                .body(request)
                .exchange((req, res) -> getResponseEntity(res));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().message()).isEqualTo(expectedMessage);
    }

}
