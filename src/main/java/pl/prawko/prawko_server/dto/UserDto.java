package pl.prawko.prawko_server.dto;

import jakarta.validation.constraints.NotNull;
import pl.prawko.prawko_server.model.User;

/**
 * Data Transfer Object used when managing an {@link User}.
 * <p>
 * This DTO is used to transfer user data between the API layer and the service layer without exposing the full model.
 */
public record UserDto(

        long id,
        @NotNull String firstName,
        @NotNull String lastName,
        @NotNull String userName,
        @NotNull String email

) {
}
