package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.Language;

import java.util.List;

public class LanguageTestData {

    public static final Language POL = new Language()
            .withId(1L)
            .withName("polish")
            .withCode("pol");
    public static final Language ENG = new Language()
            .withId(2L)
            .withName("english")
            .withCode("eng");
    public static final Language GER = new Language()
            .withId(3L)
            .withName("german")
            .withCode("ger");

    public static final List<Language> ALL = List.of(
            POL, ENG, GER
    );

}
