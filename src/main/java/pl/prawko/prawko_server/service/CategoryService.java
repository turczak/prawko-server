package pl.prawko.prawko_server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.prawko.prawko_server.model.Category;
import pl.prawko.prawko_server.repository.CategoryRepository;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository repository;

    @Override
    public Category findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(
                        () -> new RuntimeException("category: " + name + " not found"));
    }

}
