package pl.prawko.prawko_server.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.prawko.prawko_server.model.Category;
import pl.prawko.prawko_server.repository.CategoryRepository;
import pl.prawko.prawko_server.service.ICategory;

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

}
