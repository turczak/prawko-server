package pl.prawko.prawko_server.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.prawko.prawko_server.dto.RegisterDto;
import pl.prawko.prawko_server.exception.AlreadyExistsException;
import pl.prawko.prawko_server.mapper.UserMapper;
import pl.prawko.prawko_server.repository.UserRepository;
import pl.prawko.prawko_server.service.IUserService;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implementation of {@link IUserService} that manage users entities.
 */
@Service
public class UserService implements IUserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
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
    public void register(final RegisterDto dto) {
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

}
