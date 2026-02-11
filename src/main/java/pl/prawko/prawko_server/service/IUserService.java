package pl.prawko.prawko_server.service;

import pl.prawko.prawko_server.dto.RegisterDto;
import pl.prawko.prawko_server.model.User;

import java.util.Optional;

/**
 * Service interface for managing {@link User} entities.
 */
public interface IUserService {

    /**
     * Register new {@link User} using {@link RegisterDto}.
     *
     * @param dto DTO containing registration details
     */
    void register(final RegisterDto dto);

    /**
     * Checks if there is a {@link User} with same {@code userName} or {@code email}.
     *
     * @param userNameOrEmail provided name or email
     * @return true if exists
     */
    boolean checkIfExist(final String userNameOrEmail);

    /**
     * Gets an {@code user} when exists by userName or Email.
     *
     * @param userNameOrEmail provided name or email
     * @return {@code User} when found
     */
    User getByUserNameOrEmail(final String userNameOrEmail);

    /**
     * Get {@code user} by {@code id}.
     *
     * @param userId provided id
     * @return an {@code user}
     */
    Optional<User> getById(final long userId);

}
