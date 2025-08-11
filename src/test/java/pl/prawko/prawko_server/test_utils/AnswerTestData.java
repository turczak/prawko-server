package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.Answer;
import pl.prawko.prawko_server.model.AnswerTranslation;
import pl.prawko.prawko_server.model.Language;
import pl.prawko.prawko_server.model.Question;

import java.util.List;
import java.util.Map;

import static pl.prawko.prawko_server.test_utils.LanguageTestData.ENG;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.GER;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.POL;

public class AnswerTestData {

    public static final Map<Integer, Map<Character, Boolean>> SPECIAL_ANSWERS = Map.of(
            0, Map.of('A', false),
            1, Map.of('B', true),
            2, Map.of('C', false)
    );

    public static final Map<Integer, Map<Character, Boolean>> BASIC_ANSWERS = Map.of(
            0, Map.of('Y', false),
            1, Map.of('N', true)
    );

    public static final Map<Language, List<String>> SPECIAL_ANSWER_TRANSLATIONS = Map.of(
            POL, List.of(
                    "Co 60 minut.",
                    "Co 30 minut.",
                    "Co 15 minut."),
            ENG, List.of(
                    "Every 60 minutes.",
                    "Every 30 minutes.",
                    "Every 15 minutes."),
            GER, List.of(
                    "jede 60 Minuten",
                    "jede 30 Minuten",
                    "jede 15 Minuten")
    );
    public static final Map<Language, List<String>> BASIC_ANSWER_TRANSLATIONS = Map.of(
            POL, List.of(
                    "Tak.",
                    "Nie."),
            ENG, List.of(
                    "Yes.",
                    "No."),
            GER, List.of(
                    "Ja.",
                    "Nein.")
    );

    public static List<Answer> createAnswers(final Question question,
                                             final Map<Integer, Map<Character, Boolean>> labels,
                                             final Map<Language, List<String>> translations) {
        return labels.entrySet().stream()
                .flatMap(outerEntry -> {
                    int index = outerEntry.getKey();
                    return outerEntry.getValue().entrySet().stream()
                            .map(innerEntry -> {
                                var answer = new Answer()
                                        .withLabel(innerEntry.getKey())
                                        .withCorrect(innerEntry.getValue())
                                        .withQuestion(question);
                                answer.setTranslations(
                                        translations.entrySet().stream()
                                                .map(language -> new AnswerTranslation()
                                                        .withAnswer(answer)
                                                        .withLanguage(language.getKey())
                                                        .withContent(language.getValue().get(index)))
                                                .toList());
                                return answer;
                            });
                })
                .toList();
    }

}
