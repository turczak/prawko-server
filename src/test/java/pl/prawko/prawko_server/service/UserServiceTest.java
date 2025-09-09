package pl.prawko.prawko_server.service;

import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.prawko.prawko_server.dto.RegisterDto;
import pl.prawko.prawko_server.exception.AlreadyExistsException;
import pl.prawko.prawko_server.mapper.UserMapper;
import pl.prawko.prawko_server.model.User;
import pl.prawko.prawko_server.repository.UserRepository;
import pl.prawko.prawko_server.service.implementation.UserService;
import pl.prawko.prawko_server.test_utils.UserTestData;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final Map<String, String> EXPECTED = Map.of(
            "userName", "User with username 'pippin' already exists.",
            "email", "User with email 'pippin@shire.me' already exists."
    );

    private static final String ERROR_MESSAGE = "User already exists.";

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService service;

    private final RegisterDto registerDto = UserTestData.VALID_REGISTER_DTO;

    @Test
    void register_success_whenUserNotExists() {
        when(repository.existsByUserName(registerDto.userName())).thenReturn(false);
        when(repository.existsByEmail(registerDto.email())).thenReturn(false);
        final var user = new User();
        when(mapper.fromDto(registerDto)).thenReturn(user);

        service.register(registerDto);

        verify(repository).save(user);
    }

    @Test
    void register_throwAlreadyExists_whenExistsByUserName() {
        final var field = "userName";
        when(repository.existsByUserName(registerDto.userName())).thenReturn(true);
        when(repository.existsByEmail(registerDto.email())).thenReturn(false);

        final ThrowableAssert.ThrowingCallable executable = () -> service.register(registerDto);
        final var exception = catchThrowableOfType(AlreadyExistsException.class, executable);

        assertThat(exception.getMessage()).isEqualTo(ERROR_MESSAGE);
        assertThat(exception.getDetails().get(field))
                .isEqualTo(EXPECTED.get(field));
        verify(repository, never()).save(any());
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void register_throwAlreadyExists_whenExistsByEmail() {
        final var field = "email";
        when(repository.existsByUserName(registerDto.userName())).thenReturn(false);
        when(repository.existsByEmail(registerDto.email())).thenReturn(true);

        final ThrowableAssert.ThrowingCallable executable = () -> service.register(registerDto);
        final var exception = catchThrowableOfType(AlreadyExistsException.class, executable);

        assertThat(exception.getMessage()).isEqualTo(ERROR_MESSAGE);
        assertThat(exception.getDetails().get(field))
                .isEqualTo(EXPECTED.get(field));
        verify(repository, never()).save(any());
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void register_throwAlreadyExists_whenUserNameAndEmailExists() {
        when(repository.existsByUserName(registerDto.userName())).thenReturn(true);
        when(repository.existsByEmail(registerDto.email())).thenReturn(true);

        final ThrowableAssert.ThrowingCallable executable = () -> service.register(registerDto);
        final var exception = catchThrowableOfType(AlreadyExistsException.class, executable);

        assertThat(exception.getMessage()).isEqualTo(ERROR_MESSAGE);
        assertThat(exception.getDetails()).containsAllEntriesOf(EXPECTED);
        verify(repository, never()).save(any());
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getByUserNameOrEmail_returnUser_whenFoundByUserName() {
        final var userNameOrEmail = "pippin";
        final var user = UserTestData.TESTER;
        when(repository.findByUserNameOrEmail(userNameOrEmail)).thenReturn(Optional.of(user));

        final var result = service.getByUserNameOrEmail(userNameOrEmail);

        assertThat(result).isEqualTo(user);
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getByUserNameOrEmail_returnUser_whenFoundByEmail() {
        final var userNameOrEmail = "pippin@shire.me";
        final var user = UserTestData.TESTER;
        when(repository.findByUserNameOrEmail(userNameOrEmail)).thenReturn(Optional.of(user));

        final var result = service.getByUserNameOrEmail(userNameOrEmail);

        assertThat(result).isEqualTo(user);
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getByUserNameOrEmail_throwException_whenNotFound() {
        final var userNameOrEmail = "wrongUserName";
        final var errorMessage = "User with username or email '" + userNameOrEmail + "' not found.";
        when(repository.findByUserNameOrEmail(userNameOrEmail)).thenReturn(Optional.empty());

        final ThrowableAssert.ThrowingCallable executable = () -> service.getByUserNameOrEmail(userNameOrEmail);
        final var exception = catchThrowableOfType(EntityNotFoundException.class, executable);

        assertThat(exception.getMessage()).isEqualTo(errorMessage);
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(repository);
    }

}
