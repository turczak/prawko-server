package pl.prawko.prawko_server.service;

import pl.prawko.prawko_server.model.Category;

import java.util.List;

public interface ICategoryService {

    Category findByName(String name);

    List<Category> findAllFromString(String input);

}
