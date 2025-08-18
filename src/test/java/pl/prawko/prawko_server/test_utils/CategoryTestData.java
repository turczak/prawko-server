package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.Category;

import java.util.List;

public class CategoryTestData {

    public static final Category CATEGORY_PT = new Category()
            .setId(12L)
            .setName("PT");
    public static final Category CATEGORY_A = new Category()
            .setId(1L)
            .setName("A");
    public static final Category CATEGORY_B = new Category()
            .setId(5L)
            .setName("B");

    public static final List<Category> CATEGORIES_AB = List.of(
            CATEGORY_A, CATEGORY_B
    );

}
