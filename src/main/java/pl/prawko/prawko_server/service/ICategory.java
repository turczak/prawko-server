package pl.prawko.prawko_server.service;

import pl.prawko.prawko_server.model.Category;

public interface ICategory {

    Category findByName(String name);

}
