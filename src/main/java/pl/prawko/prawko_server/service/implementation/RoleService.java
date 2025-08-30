package pl.prawko.prawko_server.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pl.prawko.prawko_server.model.Role;
import pl.prawko.prawko_server.repository.RoleRepository;
import pl.prawko.prawko_server.service.IRoleService;

/**
 * Implementation of {@link IRoleService} that manages {@link Role} entities.
 */
@Service
public class RoleService implements IRoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     *
     * @throws EntityNotFoundException when role doesn't exists
     */
    @Override
    public Role getByName(final String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Role with name '" + name + "' not found."));
    }

}
