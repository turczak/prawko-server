package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.Language;
import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionTranslation;

import java.util.List;
import java.util.Map;

public class QuestionTranslationsTestData {

    public static List<QuestionTranslation> createTranslations(final Question question,
                                                               final Map<Language, String> map) {
        return map.entrySet().stream()
                .map(entry -> new QuestionTranslation()
                        .withQuestion(question)
                        .withLanguage(entry.getKey())
                        .withContent(entry.getValue()))
                .toList();
    }

}
