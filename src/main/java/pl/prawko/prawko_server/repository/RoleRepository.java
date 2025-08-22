package pl.prawko.prawko_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.prawko.prawko_server.model.Role;

import java.util.Optional;

/**
 * Repository for {@link Role} entities.
 * <p>
 * Provides standard CRUD operations through {@link JpaRepository} and custom methods.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * This method looking for existing role with provided name and return it.
     *
     * @param name provided name of the role
     * @return role when found, otherwise empty
     */
    Optional<Role> findByName(String name);

}
