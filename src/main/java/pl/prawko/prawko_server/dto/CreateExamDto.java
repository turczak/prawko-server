package pl.prawko.prawko_server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object representing create exam request with {@code userId} and {@code categoryName}
 * <p>
 * This DTO is used to transfer exam creation data between the API layer and the service layer.
 */
public record CreateExamDto(

        @NotEmpty(message = "userId is required")
        long userId,

        @NotBlank(message = "category is required")
        @Size(min = 1, max = 2)
        String categoryName
        
) {
}
