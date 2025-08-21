package pl.prawko.prawko_server.dto;

import org.springframework.http.ResponseEntity;

/**
 * Custom body of {@link ResponseEntity}
 *
 * @param message response text
 */
public record ApiResponse(String message) {
}
