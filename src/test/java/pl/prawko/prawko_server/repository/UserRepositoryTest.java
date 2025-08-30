package pl.prawko.prawko_server.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.prawko.prawko_server.model.User;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(result).isTrue();
    }

    @Test
    void existsByUserName_returnFalse() {
        final var result = repository.existsByUserName("nonExistingUserName");
        assertThat(result).isFalse();
    }

    @Test
    void existsByEmail_returnTrue() {
        final var result = repository.existsByEmail(tester.getEmail());
        assertThat(result).isTrue();
    }

    @Test
    void existsByEmail_returnFalse() {
        final var result = repository.existsByEmail("nonExistingEmail");
        assertThat(result).isFalse();
    }

}
