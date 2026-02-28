package pl.prawko.prawko_server.dto;

import org.springframework.lang.NonNull;
import pl.prawko.prawko_server.model.User;

/**
 * Data Transfer Object used when managing an {@link User}.
 * <p>
 * This DTO is used to transfer user data between the API layer and the service layer without exposing the full model.
 */
public record UserDto(

        long id,
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull String userName,
        @NonNull String email

) {
}
