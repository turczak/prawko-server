package pl.prawko.prawko_server.service;

import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.prawko.prawko_server.model.Role;
import pl.prawko.prawko_server.repository.RoleRepository;
import pl.prawko.prawko_server.service.implementation.RoleService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository repository;

    @InjectMocks
    private RoleService service;

    @Test
    void getByName_returnRole_whenFound() {
        final var role = new Role().setName("TEST");
        when(repository.findByName(role.getName())).thenReturn(Optional.of(role));

        final var result = service.getByName(role.getName());

        assertThat(result).isEqualTo(role);
    }

    @Test
    void getByName_throwNotFound_whenNotFound() {
        final var roleName = "nonExistingRole";
        when(repository.findByName(roleName)).thenReturn(Optional.empty());

        final ThrowableAssert.ThrowingCallable result = () -> service.getByName(roleName);

        assertThatThrownBy(result)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Role with name 'nonExistingRole' not found.");
    }

}
