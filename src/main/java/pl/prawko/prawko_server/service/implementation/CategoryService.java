package pl.prawko.prawko_server.service.implementation;

import org.springframework.stereotype.Service;
import pl.prawko.prawko_server.model.Category;
import pl.prawko.prawko_server.repository.CategoryRepository;
import pl.prawko.prawko_server.service.ICategoryService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository repository;

    public CategoryService(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category findByName(final String name) {
        return repository.findByName(name)
                .orElseThrow(
                        () -> new RuntimeException("category: " + name + " not found"));
    }

    @Override
    public List<Category> findAllFromString(final String input) {
        return Arrays.stream(input.split(","))
                .map(repository::findByName)
                .flatMap(Optional::stream)
                .toList();
    }

}
