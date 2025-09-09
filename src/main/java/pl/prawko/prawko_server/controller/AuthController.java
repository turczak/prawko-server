package pl.prawko.prawko_server.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.prawko.prawko_server.dto.ApiResponse;
import pl.prawko.prawko_server.dto.LoginRequest;

/**
 * REST controller to authenticate users using {@link LoginRequest}.
 * <p>
 * Mapped on {@code /auth}
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Method to authorize user using {@link LoginRequest} via POST.
     *
     * @param request request with login and password
     * @return success when user is authorized
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse login(@Valid @RequestBody final LoginRequest request) {
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.userName(),
                request.password()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ApiResponse("User signed-in successfully.");
    }

}
