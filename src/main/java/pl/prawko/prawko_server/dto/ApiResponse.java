package pl.prawko.prawko_server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * Custom body of {@link ResponseEntity} used to return responses in REST Controllers.
 *
 * @param message response text
 */
public record ApiResponse(

        @NonNull String message,

        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Map<String, String> details

) {

    public ApiResponse(final String message) {
        this(message, null);
    }

}
