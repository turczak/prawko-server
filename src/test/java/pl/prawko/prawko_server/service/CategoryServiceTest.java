package pl.prawko.prawko_server.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.prawko.prawko_server.model.Category;
import pl.prawko.prawko_server.repository.CategoryRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryService service;

    @Test
    void shouldFindByName() {
        final var name = "B5";
        final var id = 5L;
        final var expected = new Category()
                .withId(id)
                .withName(name);
        when(repository.findByName(name))
                .thenReturn(Optional.of(expected));
        final var result = service.findByName(name);
        assertThat(result)
                .satisfies(category ->
                        assertThat(category)
                                .isEqualTo(expected));
    }

}
