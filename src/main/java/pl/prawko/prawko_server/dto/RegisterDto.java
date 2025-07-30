package pl.prawko.prawko_server.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDto(

        @NotBlank(message = "First name is required.")
        @Size(max = 31, message = "First name is too long.")
        String firstName,

        @NotBlank(message = "Last name is required.")
        @Size(max = 31, message = "Last name is too long.")
        String lastName,

        @NotBlank(message = "Username is required.")
        @Size(min = 3, max = 31, message = "Username must be between 3 and 31 characters.")
        String userName,

        @NotBlank(message = "Email is required.")
        @Size(max = 63, message = "Email is too long.")
        @Email(message = "Email should be valid.")
        String email,

        @NotBlank(message = "Password is required.")
        @Size(min = 7, max = 63, message = "Password must be between 7 and 63 characters.")
        String password

) {
}
