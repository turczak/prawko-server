package pl.prawko.prawko_server.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.prawko.prawko_server.dto.RegisterDto;
import pl.prawko.prawko_server.exception.AlreadyExistsException;
import pl.prawko.prawko_server.mapper.UserMapper;
import pl.prawko.prawko_server.model.User;
import pl.prawko.prawko_server.repository.UserRepository;
import pl.prawko.prawko_server.service.implementation.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

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
        when(repository.existsByUserName(registerDto.userName())).thenReturn(true);
        when(repository.existsByEmail(registerDto.email())).thenReturn(false);

        final Executable executable = () -> service.register(registerDto);
        final var exception = assertThrows(AlreadyExistsException.class, executable);

        assertThat(exception.getMessage()).isEqualTo("User already exists.");
        assertThat(exception.getDetails().get("userName")).isEqualTo("User with username 'pippin' already exists.");

        verify(repository, never()).save(any());
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void register_throwAlreadyExists_whenExistsByEmail() {
        when(repository.existsByUserName(registerDto.userName())).thenReturn(false);
        when(repository.existsByEmail(registerDto.email())).thenReturn(true);

        final Executable executable = () -> service.register(registerDto);
        final var exception = assertThrows(AlreadyExistsException.class, executable);

        assertThat(exception.getMessage()).isEqualTo("User already exists.");
        assertThat(exception.getDetails().get("email")).isEqualTo("User with email 'pippin@shire.me' already exists.");

        verify(repository, never()).save(any());
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void register_throwAlreadyExists_whenUserNameAndEmailExists() {
        when(repository.existsByUserName(registerDto.userName())).thenReturn(true);
        when(repository.existsByEmail(registerDto.email())).thenReturn(true);

        final Executable executable = () -> service.register(registerDto);
        final var exception = assertThrows(AlreadyExistsException.class, executable);
        final var errors = exception.getDetails();

        assertThat(errors)
                .hasSize(2)
                .containsKeys("userName", "email");

        verify(repository, never()).save(any());
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(repository);
    }


}
