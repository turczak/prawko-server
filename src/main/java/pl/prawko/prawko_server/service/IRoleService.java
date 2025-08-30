package pl.prawko.prawko_server.service;

import pl.prawko.prawko_server.model.Role;

/**
 * Service interface for managing {@link Role} entities.
 */
public interface IRoleService {

    /**
     * Gets a matching role with the provided role name.
     *
     * @param name role name that should be looked for
     * @return matching role
     */
    Role getByName(final String name);

}
