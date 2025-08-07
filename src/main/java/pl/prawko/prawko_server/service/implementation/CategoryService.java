package pl.prawko.prawko_server.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.prawko.prawko_server.model.Category;
import pl.prawko.prawko_server.repository.CategoryRepository;
import pl.prawko.prawko_server.service.ICategory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService implements ICategory {

    private final CategoryRepository repository;

    @Override
    public Category findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(
                        () -> new RuntimeException("category: " + name + " not found"));
    }

    @Override
    public List<Category> findAllFromString(String input) {
        return Arrays.stream(input.split(","))
                .map(repository::findByName)
                .flatMap(Optional::stream)
                .toList();
    }

}
