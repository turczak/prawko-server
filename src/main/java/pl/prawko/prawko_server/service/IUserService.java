package pl.prawko.prawko_server.service;

import pl.prawko.prawko_server.dto.RegisterDto;
import pl.prawko.prawko_server.model.User;

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
     * Get {@code user} by {@code id}.
     *
     * @param userId provided id
     * @return an {@code user}
     */
    User getById(final long userId);

}
