package pl.prawko.prawko_server.mapper;

import org.springframework.stereotype.Component;
import pl.prawko.prawko_server.dto.RegisterDto;
import pl.prawko.prawko_server.model.User;
import pl.prawko.prawko_server.service.implementation.RoleService;

import java.util.List;

/**
 * This class is responsible for mapping {@link RegisterDto} to {@link User} entity.
 * <p>
 * The mapper is registered as a Spring Component, so it can be injected into services or other components that require user mapping functionality.
 */
@Component
public class UserMapper {

    private RoleService roleService;

    /**
     * This method is mapping {@link RegisterDto} into {@link User} entity.
     *
     * @param dto provided dto to map user from it
     * @return mapped user with standard role and encoded password
     */
    public User fromDto(final RegisterDto dto) {
        return new User()
                .setFirstName(dto.firstName())
                .setLastName(dto.lastName())
                .setUserName(dto.userName())
                .setEmail(dto.email())
                .setPassword(dto.password())
                .setRoles(List.of(roleService.findByName("USER")))
                .setEnabled(true);
    }

}
