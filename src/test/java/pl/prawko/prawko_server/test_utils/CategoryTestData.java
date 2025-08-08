package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.Category;

import java.util.List;

public class CategoryTestData {

    public static final Category CATEGORY_PT = new Category()
            .withId(12L)
            .withName("PT");
    public static final Category CATEGORY_A = new Category()
            .withId(1L)
            .withName("A");
    public static final Category CATEGORY_B = new Category()
            .withId(5L)
            .withName("B");

    public static final List<Category> CATEGORIES_AB = List.of(
            CATEGORY_A, CATEGORY_B
    );

}
