package pl.prawko.prawko_server.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;
import pl.prawko.prawko_server.dto.ApiResponse;
import pl.prawko.prawko_server.test_utils.IntegrationTest;
import pl.prawko.prawko_server.test_utils.UserTestData;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.prawko.prawko_server.test_utils.TestUtils.BASE_URL;
import static pl.prawko.prawko_server.test_utils.TestUtils.getResponseEntity;

@IntegrationTest
public class AuthControllerTest {

    private static final String URL = "/auth";

    @LocalServerPort
    private int port;

    private RestClient restClient;

    @BeforeEach
    void setUp() {
        restClient = RestClient.builder()
                .baseUrl(BASE_URL + port)
                .build();
    }

    @Test
    void login_returnsOk_whenCredentialsValid() {
        final var request = UserTestData.VALID_LOGIN_REQUEST;
        final var message = "User signed-in successfully.";
        final var response = restClient.post()
                .uri(URL)
                .body(request)
                .retrieve()
                .toEntity(ApiResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().message()).isEqualTo(message);
    }

    @Test
    void login_returnsUnauthorized_whenCredentialsInvalid() {
        final var request = UserTestData.INVALID_LOGIN_REQUEST;
        final var message = "Invalid login or password.";
        final var response = restClient.post()
                .uri(URL)
                .body(request)
                .exchange((req, res) -> getResponseEntity(res));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().message()).isEqualTo(message);
    }

}
