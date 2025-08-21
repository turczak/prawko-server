package pl.prawko.prawko_server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import pl.prawko.prawko_server.dto.ApiResponse;

/**
 * Handles exceptions in REST controllers.
 * <p>
 * Returns error responses using {@link ApiResponse}
 */
@RestControllerAdvice
public class ExceptionController {

    /**
     * Handle cases when request is missing required file.
     *
     * @param exception exception thrown when a required file is missing
     * @return an error response
     */
    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleMissingFile(final MissingServletRequestPartException exception) {
        return new ApiResponse("Required part 'file' is not present.");
    }

    /**
     * Handle cases when request contain wrong type of file.
     *
     * @param exception exception thrown when a required file is wrong type
     * @return an error response
     */
    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ApiResponse handleWrongFileType(final MultipartException exception) {
        return new ApiResponse(exception.getMessage());
    }

}
