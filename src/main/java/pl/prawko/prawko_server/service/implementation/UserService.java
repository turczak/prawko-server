package pl.prawko.prawko_server.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.prawko.prawko_server.dto.RegisterDto;
import pl.prawko.prawko_server.exception.AlreadyExistsException;
import pl.prawko.prawko_server.mapper.UserMapper;
import pl.prawko.prawko_server.model.Role;
import pl.prawko.prawko_server.model.User;
import pl.prawko.prawko_server.repository.UserRepository;
import pl.prawko.prawko_server.service.IUserService;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of {@link IUserService} that manage users entities.
 * <p>
 * It also implements {@link UserDetailsService} for authentication purposes.
 */
@Service
public class UserService implements IUserService, UserDetailsService {

    @NonNull
    private final UserRepository repository;
    @NonNull
    private final UserMapper mapper;

    public UserService(@NonNull final UserRepository repository,
                       @NonNull final UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     *
     * @throws AlreadyExistsException if a user with the same username or email already exists
     */
    @Override
    @Transactional
    public void register(@NonNull final RegisterDto dto) {
        final Map<String, String> errorDetails = new LinkedHashMap<>();
        if (repository.existsByUserName(dto.userName())) {
            errorDetails.put("userName", "User with username '" + dto.userName() + "' already exists.");
        }
        if (repository.existsByEmail(dto.email())) {
            errorDetails.put("email", "User with email '" + dto.email() + "' already exists.");
        }
        if (!errorDetails.isEmpty()) {
            throw new AlreadyExistsException("User already exists.", errorDetails);
        }
        final var user = mapper.fromDto(dto);
        repository.save(user);
    }

    /**
     * Checks if entity exists by userName or email.
     *
     * @param userNameOrEmail provided name or email to look for
     * @return {@code true} if entity exist
     */
    @Override
    public boolean checkIfExist(@NonNull final String userNameOrEmail) {
        return repository.existsByUserName(userNameOrEmail) || repository.existsByEmail(userNameOrEmail);
    }

    /**
     * {@inheritDoc}
     *
     * @throws EntityNotFoundException if the user with provided userName or email doesn't exist
     */
    @Nullable
    @Override
    public User getByUserNameOrEmail(@NonNull final String userNameOrEmail) {
        return repository.findByUserNameOrEmail(userNameOrEmail)
                .orElseThrow(() -> new EntityNotFoundException("User with username or email '" + userNameOrEmail + "' not found."));
    }

    /**
     * Load user-specific data during authentication.
     * <p>
     *
     * @param userNameOrEmail the userName or email identifying the user
     * @return {@link org.springframework.security.core.userdetails.User} object with granted authorities based on user's roles
     * @throws UsernameNotFoundException if user have not been found with the provided details
     */
    @Nullable
    @Override
    public UserDetails loadUserByUsername(final String userNameOrEmail) throws UsernameNotFoundException {
        if (checkIfExist(userNameOrEmail)) {
            final var user = getByUserNameOrEmail(userNameOrEmail);
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
        } else {
            throw new UsernameNotFoundException("Invalid login or password.");
        }
    }

    @NonNull
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(@NonNull final Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .toList();
    }

    /**
     * {@inheritDoc}
     *
     * @throws EntityNotFoundException if a user with provided id have not been found
     */
    @Override
    public Optional<User> getById(long userId) {
        return repository.findById(userId);
    }

}
