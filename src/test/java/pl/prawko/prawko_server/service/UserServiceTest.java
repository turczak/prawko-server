package pl.prawko.prawko_server.service;

import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    private RegisterDto registerDto;

    @BeforeEach
    void setUp() {
        registerDto = new RegisterDto(
                "Peregrin",
                "Tuk",
                "pippin",
                "pippin@shire.me",
                "lebmas");
    }

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
    void getById_returnUser_whenFound() {
        final var given = 44L;
        final var expected = UserTestData.TESTER;
        when(repository.findById(given)).thenReturn(Optional.of(expected));

        final var result = service.getById(given);

        assertThat(result).isEqualTo(expected);
        verify(repository).findById(given);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getById_throwException_whenNotFound() {
        final var given = 666L;
        when(repository.findById(given)).thenReturn(Optional.empty());

        final ThrowableAssert.ThrowingCallable result = () -> service.getById(given);

        assertThatThrownBy(result)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("User with '" + given + "' not found.");
    }

}
