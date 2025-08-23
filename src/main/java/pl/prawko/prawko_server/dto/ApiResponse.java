package pl.prawko.prawko_server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

public record ApiResponse(

        String message,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Map<String, String> details

) {

    /**
     * Constructs {@code ApiResponse} without details.
     *
     * @param message provided text
     */
    public ApiResponse(final String message) {
        this(message, null);
    }

}
