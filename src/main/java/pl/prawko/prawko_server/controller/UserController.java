package pl.prawko.prawko_server.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.prawko.prawko_server.dto.ApiResponse;
import pl.prawko.prawko_server.dto.RegisterDto;
import pl.prawko.prawko_server.exception.AlreadyExistsException;
import pl.prawko.prawko_server.model.User;
import pl.prawko.prawko_server.service.implementation.UserService;

/**
 * REST controller for managing {@link User} entities using http requests mapped on {@code /users}.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * POST method to register new {@link User}.
     * <p>
     * Errors are handled via {@link ExceptionController#handleAlreadyExists(AlreadyExistsException)}
     *
     * @param dto DTO used to transfer details of registration
     * @return status 200 with success message
     */
    @PostMapping
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody final RegisterDto dto) {
        userService.register(dto);
        return ResponseEntity.ok()
                .body(new ApiResponse("User registered successfully."));
    }

}
