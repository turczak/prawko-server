package pl.prawko.prawko_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.prawko.prawko_server.model.User;

/**
 * Repository for {@link User} entities.
 * <p>
 * Provides standard CRUD operations through {@link JpaRepository} and custom methods.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Checks whether {@link User} already exists with specified username.
     *
     * @param userName provided username to check for existence
     * @return {@code true} if a user already exists, {@code false} otherwise
     */
    boolean existsByUserName(final String userName);

    /**
     * Checks whether {@link User} already exists with specified email.
     *
     * @param email provided email to check for existence
     * @return {@code true} if a user already exists, {@code false} otherwise
     */
    boolean existsByEmail(final String email);

}
