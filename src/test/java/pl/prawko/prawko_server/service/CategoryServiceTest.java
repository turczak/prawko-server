package pl.prawko.prawko_server.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.prawko.prawko_server.model.Category;
import pl.prawko.prawko_server.repository.CategoryRepository;
import pl.prawko.prawko_server.service.implementation.CategoryService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static pl.prawko.prawko_server.test_utils.CategoryTestData.CATEGORIES_AB;
import static pl.prawko.prawko_server.test_utils.CategoryTestData.CATEGORY_A;
import static pl.prawko.prawko_server.test_utils.CategoryTestData.CATEGORY_B;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryService service;

    @Test
    void findByName_returnCategory_whenFound() {
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

    @Test
    void findByName_throwException_whenCategoryNotFound() {
        final var name = "wrong";
        when(repository.findByName(name))
                .thenReturn(Optional.empty());
        assertThatThrownBy(
                () -> service.findByName(name))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("category: " + name + " not found");
    }

    @Test
    void findAllFromString_returnCategories_whenFound() {
        when(repository.findByName("A"))
                .thenReturn(Optional.of(CATEGORY_A));
        when(repository.findByName("B"))
                .thenReturn(Optional.of(CATEGORY_B));
        final var result = service.findAllFromString("A,B");
        assertThat(result)
                .isEqualTo(CATEGORIES_AB);
    }

    @Test
    void findAllFromString_skipCategories_whenNotFound() {
        when(repository.findByName("Z"))
                .thenReturn(Optional.empty());
        when(repository.findByName("B"))
                .thenReturn(Optional.of(CATEGORY_B));
        final var result = service.findAllFromString("Z,B");
        assertThat(result)
                .hasSize(1)
                .isEqualTo(
                        List.of(CATEGORY_B));
    }

}
