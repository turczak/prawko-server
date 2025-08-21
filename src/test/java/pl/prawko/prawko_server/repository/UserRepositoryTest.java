package pl.prawko.prawko_server.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.prawko.prawko_server.model.User;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    private User tester;

    @BeforeEach
    void setUp() {
        tester = new User()
                .setUserName("pippin")
                .setEmail("pippin@shire.me");
        repository.save(tester);
    }

    @Test
    void existsByUserName_returnTrue() {
        final var result = repository.existsByUserName(tester.getUserName());
        assertTrue(result);
    }

    @Test
    void existsByUserName_returnFalse() {
        final var result = repository.existsByUserName("nonExistingUserName");
        assertFalse(result);
    }

    @Test
    void existsByEmail_returnTrue() {
        final var result = repository.existsByEmail(tester.getEmail());
        assertTrue(result);
    }

    @Test
    void existsByEmail_returnFalse() {
        final var result = repository.existsByEmail("nonExistingEmail");
        assertFalse(result);
    }

}
