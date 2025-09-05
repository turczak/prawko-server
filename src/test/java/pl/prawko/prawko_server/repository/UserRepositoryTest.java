package pl.prawko.prawko_server.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.prawko.prawko_server.model.User;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    private final String userName = "pippin";
    private final String email = "pippin@shire.me";
    private final String wrongUserName = "nonExistingUserName";
    private final String wrongEmail = "nonExistingEmail";

    @Autowired
    private UserRepository repository;

    private User tester;

    @BeforeEach
    void setUp() {
        tester = new User()
                .setUserName(userName)
                .setEmail(email);
        repository.save(tester);
    }

    @Test
    void existsByUserName_returnTrue() {
        final var result = repository.existsByUserName(userName);
        assertThat(result).isTrue();
    }

    @Test
    void existsByUserName_returnFalse() {
        final var result = repository.existsByUserName(wrongUserName);
        assertThat(result).isFalse();
    }

    @Test
    void existsByEmail_returnTrue() {
        final var result = repository.existsByEmail(tester.getEmail());
        assertThat(result).isTrue();
    }

    @Test
    void existsByEmail_returnFalse() {
        final var result = repository.existsByEmail(wrongEmail);
        assertThat(result).isFalse();
    }

    @Test
    void getByUserName_returnUser() {
        final var result = repository.findByUserName(userName);
        assertThat(result.get().getUserName()).isEqualTo(userName);
    }

    @Test
    void getByUserName_returnEmpty() {
        final var result = repository.findByUserName(wrongUserName);
        assertThat(result).isEmpty();
    }

    @Test
    void getByEmail_returnUser() {
        final var result = repository.findByEmail(email);
        assertThat(result.get().getEmail()).isEqualTo(email);
    }

    @Test
    void getByEmail_returnEmpty() {
        final var result = repository.findByEmail(wrongEmail);
        assertThat(result).isEmpty();
    }

}
