package pl.prawko.prawko_server.exception;

import java.util.Map;

/**
 * Custom exception with error details to throw when entity already exists.
 */
public class AlreadyExistsException extends RuntimeException {

    private final Map<String, String> details;

    public AlreadyExistsException(final String message, final Map<String, String> details) {
        super(message);
        this.details = details;
    }

    public Map<String, String> getDetails() {
        return details;
    }

}
