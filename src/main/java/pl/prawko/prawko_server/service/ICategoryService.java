package pl.prawko.prawko_server.service;

import pl.prawko.prawko_server.model.Category;

public interface ICategoryService {

    Category findByName(String name);

}
