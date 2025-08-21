package pl.prawko.prawko_server.dto;

import pl.prawko.prawko_server.model.User;

/**
 * Data Transfer Object used when managing an {@link User}.
 * <p>
 * This DTO is used to transfer user data between the API layer and the service layer without exposing full model.
 */
public record UserDto(

        long id,
        String firstName,
        String lastName,
        String userName,
        String email

) {
}
