package pl.prawko.prawko_server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import pl.prawko.prawko_server.dto.ApiResponse;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleMissingFile(final MissingServletRequestPartException exception) {
        return new ApiResponse("Required part 'file' is not present.");
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ApiResponse handleWrongFileType(final MultipartException exception) {
        return new ApiResponse(exception.getMessage());
    }

}
