package pl.prawko.prawko_server.dto;

import jakarta.validation.constraints.NotNull;
import pl.prawko.prawko_server.model.CategoryVariant;

/**
 * Data Transfer Object representing create exam request with {@code userId} and {@code categoryName}
 * <p>
 * This DTO is used to transfer exam creation data between the API layer and the service layer.
 */
public record CreateExamDto(

        @NotNull(message = "userId is required")
        Long userId,

        @NotNull(message = "category is required")
        CategoryVariant categoryName

) {
}
