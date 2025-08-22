package pl.prawko.prawko_server.mapper;

import org.springframework.stereotype.Component;
import pl.prawko.prawko_server.dto.RegisterDto;
import pl.prawko.prawko_server.model.User;

/**
 * This class is responsible for mapping {@link RegisterDto} to {@link User} entity.
 * <p>
 * The mapper is registered as a Spring Component, so it can be injected into services or other components that require user mapping functionality.
 */
@Component
public class UserMapper {

    /**
     *
     * @param dto
     * @return
     */
    public User fromDto(final RegisterDto dto) {
        return null;
    }

}
