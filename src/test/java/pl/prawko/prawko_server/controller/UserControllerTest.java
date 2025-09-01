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
import pl.prawko.prawko_server.test_utils.TestUtils;
import pl.prawko.prawko_server.test_utils.UserTestData;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestSecurityConfig.class)
public class UserControllerTest {

    private static final String URL = "/users";

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
    void registerUser_success_whenDtoIsValid() {
        final var expected = "User registered successfully.";
        final var dto = UserTestData.VALID_REGISTER_DTO;

        final var response = restClient.post()
                .uri(URL)
                .body(dto)
                .retrieve()
                .toEntity(ApiResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().message()).isEqualTo(expected);
    }

    @Test
    void registerUser_returnBadRequest_whenDtoIsInvalid() {
        final var dto = UserTestData.INVALID_REGISTER_DTO;
        final var errorMessage = "Validation for request failed.";
        final var errorDetails = Map.of(
                "firstName", "First name is too long.",
                "lastName", "Last name is required.",
                "userName", "Username must be between 3 and 31 characters.",
                "email", "Email should be valid.",
                "password", "Password must be between 7 and 63 characters."
        );

        final var response = restClient.post()
                .uri(URL)
                .body(dto)
                .exchange((req, res) -> TestUtils.getResponseEntity(res));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().message()).isEqualTo(errorMessage);
        assertThat(response.getBody().details()).isEqualTo(errorDetails);
    }

    @Test
    void registerUser_returnConflict_whenUserAlreadyExists() {
        final var dto = UserTestData.VALID_REGISTER_DTO;
        final var errorMessage = "User already exists.";
        final var errorDetails = Map.of(
                "email", "User with email 'pippin@shire.me' already exists.",
                "userName", "User with username 'pippin' already exists."
        );
        restClient.post()
                .uri(URL)
                .body(dto)
                .exchange((req, res) -> TestUtils.getResponseEntity(res));

        final var response = restClient.post()
                .uri(URL)
                .body(dto)
                .exchange((req, res) -> TestUtils.getResponseEntity(res));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody().message()).isEqualTo(errorMessage);
        assertThat(response.getBody().details()).isEqualTo(errorDetails);
    }

}
