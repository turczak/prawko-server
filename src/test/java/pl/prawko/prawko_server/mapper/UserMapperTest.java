package pl.prawko.prawko_server.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.prawko.prawko_server.model.Role;
import pl.prawko.prawko_server.service.implementation.RoleService;
import pl.prawko.prawko_server.test_utils.UserTestData;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @Mock
    private RoleService roleService;

    @InjectMocks
    private UserMapper mapper;

    private static final String[] IGNORED_FIELDS = {"id", "created", "updated", "exams"};

    @Test
    void fromDto_correctlyMapUser() {
        final var dto = UserTestData.VALID_REGISTER_DTO;
        final var role = new Role().setName("USER");
        final var expected = UserTestData.TESTER;
        when(roleService.getByName(role.getName())).thenReturn(role);

        final var result = mapper.fromDto(dto);

        assertThat(result)
                .usingRecursiveComparison()
                .ignoringFields(IGNORED_FIELDS)
                .isEqualTo(expected);
    }

}
