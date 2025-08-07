package pl.prawko.prawko_server.util;

import pl.prawko.prawko_server.model.*;

import java.util.List;
import java.util.Map;

public class TestDataUtil {

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
    public static final List<Language> LANGUAGES = List.of(
            POL,
            ENG,
            GER
    );
    public static final Category CATEGORY = new Category()
            .withId(12L)
            .withName("PT");
    private static final String
            QUESTION_CONTENT_POL = "Jak często należy obracać poszkodowanego nieurazowego na drugi bok po ułożeniu go w pozycji bezpiecznej?";
    private static final String
            QUESTION_CONTENT_ENG = "How often should you turn a non-traumatic victim to the other side after laying him in the recovery position?";
    private static final String
            QUESTION_CONTENT_GER = "Wie oft soll man einen symptomlosen Betroffenen auf die andere Körperseite nach dem Legen in stabiler Seitenlage drehen?";
    private static final String ANSWER_A_POL = "Co 60 minut.";
    private static final String ANSWER_B_POL = "Co 30 minut.";
    private static final String ANSWER_C_POL = "Co 15 minut.";
    private static final String ANSWER_A_ENG = "Every 60 minutes.";
    private static final String ANSWER_B_ENG = "Every 30 minutes.";
    private static final String ANSWER_C_ENG = "Every 15 minutes.";
    private static final String ANSWER_A_GER = "jede 60 Minuten";
    private static final String ANSWER_B_GER = "jede 30 Minuten";
    private static final String ANSWER_C_GER = "jede 15 Minuten";

    public static final QuestionCSV QUESTION_CSV = new QuestionCSV(
            "PD10(3)",
            2143,
            QUESTION_CONTENT_POL,
            ANSWER_A_POL,
            ANSWER_B_POL,
            ANSWER_C_POL,
            QUESTION_CONTENT_ENG,
            ANSWER_A_ENG,
            ANSWER_B_ENG,
            ANSWER_C_ENG,
            QUESTION_CONTENT_GER,
            ANSWER_A_GER,
            ANSWER_B_GER,
            ANSWER_C_GER,
            'B',
            "R_101org.jpg",
            "SPECJALISTYCZNY",
            2,
            "PT"
    );

    public static final Question QUESTION = new Question()
            .withName("PD10(3)")
            .withId(2143)
            .withMedia("R_101org.jpg")
            .withType(QuestionType.SPECIAL)
            .withValue(2)
            .withCategories
                    (List.of(CATEGORY))
            .withTranslations(
                    questionTranslations())
            .withAnswers(
                    List.of(
                            createAnswer('A', false),
                            createAnswer('B', true),
                            createAnswer('C', false)
                    ));

    private static Answer createAnswer(final char label,
                                       final boolean isCorrect) {
        final var answer = new Answer()
                .withLabel(label)
                .withCorrect(isCorrect)
                .withQuestion(QUESTION);
        answer.setTranslations(
                answerTranslations(answer));
        return answer;
    }

    private static List<AnswerTranslation> answerTranslations(final Answer answer) {
        final Map<Language, List<String>> translationsByLanguage = Map.of(
                POL, List.of(
                        ANSWER_A_POL,
                        ANSWER_B_POL,
                        ANSWER_C_POL),
                ENG, List.of(
                        ANSWER_A_ENG,
                        ANSWER_B_ENG,
                        ANSWER_C_ENG),
                GER, List.of(
                        ANSWER_A_GER,
                        ANSWER_B_GER,
                        ANSWER_C_GER)
        );
        return translationsByLanguage.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .map(content -> new AnswerTranslation()
                                .withAnswer(answer)
                                .withLanguage(entry.getKey())
                                .withContent(content)))
                .toList();
    }

    private static QuestionTranslation createQuestionTranslation(final Language language,
                                                                 final String content) {
        return new QuestionTranslation()
                .withContent(content)
                .withLanguage(language)
                .withQuestion(QUESTION);
    }

    private static List<QuestionTranslation> questionTranslations() {
        return Map.of(
                        POL, QUESTION_CONTENT_POL,
                        ENG, QUESTION_CONTENT_ENG,
                        GER, QUESTION_CONTENT_GER)
                .entrySet().stream()
                .map(entry ->
                        createQuestionTranslation(
                                entry.getKey(),
                                entry.getValue()))
                .toList();
    }

}
