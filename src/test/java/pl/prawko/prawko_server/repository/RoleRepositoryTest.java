package pl.prawko.prawko_server.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.prawko.prawko_server.model.Role;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository repository;

    private final Role expected = new Role().setName("TEST");

    @Test
    void findByName_returnRole_whenExists() {
        repository.save(expected);

        final var result = repository.findByName(expected.getName());

        assertEquals("TEST", result.get().getName());
    }

    @Test
    void findByName_returnEmpty_whenNotExists() {
        final var result = repository.findByName(expected.getName());

        assertThat(result).isNotPresent();
    }

}
