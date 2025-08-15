package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.Language;

import java.util.List;

public class LanguageTestData {

    public static final Language POL = new Language()
            .setId(1L)
            .setName("polish")
            .setCode("pol");
    public static final Language ENG = new Language()
            .setId(2L)
            .setName("english")
            .setCode("eng");
    public static final Language GER = new Language()
            .setId(3L)
            .setName("german")
            .setCode("ger");

    public static final List<Language> ALL = List.of(
            POL, ENG, GER
    );

}
