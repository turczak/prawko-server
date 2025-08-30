package pl.prawko.prawko_server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import pl.prawko.prawko_server.dto.ApiResponse;

/**
 * Handle exceptions in REST controllers.
 * <p>
 * Returns error responses using {@link ApiResponse} with {@code HttpStatus}
 */
@RestControllerAdvice
public class ExceptionController {

    /**
     * Handles cases when a request doesn't contain the required file.
     *
     * @param exception exception thrown when a required file is missing
     * @return an error response with 400 Bad Request
     */
    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleMissingFile(final MissingServletRequestPartException exception) {
        return new ApiResponse("Required part 'file' is not present.");
    }

    /**
     * Handles cases when the request contains the wrong file type.
     *
     * @param exception exception thrown when a required file is the wrong type
     * @return an error response with 415 Unsupported Media Type
     */
    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ApiResponse handleWrongFileType(final MultipartException exception) {
        return new ApiResponse(exception.getMessage());
    }

}
