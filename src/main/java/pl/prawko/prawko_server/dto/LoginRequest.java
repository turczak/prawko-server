package pl.prawko.prawko_server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object used to log in into the application.
 * <p>
 * This DTO is used to transfer user data between the API layer and the service layer, applying validation.
 *
 * @param userName required, between 3 and 31 characters
 * @param password required, between 7 and 63 characters
 */
public record LoginRequest(

        @NotBlank(message = "Username is required.")
        @Size(min = 3, max = 31, message = "Username must be between 3 and 31 characters.")
        String userName,

        @NotBlank(message = "Password is required.")
        @Size(min = 7, max = 63, message = "Password must be between 7 and 63 characters.")
        String password

) {
}
