package pl.prawko.prawko_server.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import pl.prawko.prawko_server.model.Category;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    void findByName_returnCategory() {
        final var category = new Category().withName("C4");
        repository.save(category);
        final var result = repository.findByName("C4");
        assertThat(result)
                .isPresent();
        assertThat(result.get().getName())
                .isEqualTo("C4");
    }

    @Test
    void findByName_returnEmpty() {
        final var result = repository.findByName("WRONG");
        assertThat(result)
                .isEmpty();
    }

}
