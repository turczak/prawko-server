package pl.prawko.prawko_server.util;

import pl.prawko.prawko_server.model.Answer;
import pl.prawko.prawko_server.model.AnswerTranslation;
import pl.prawko.prawko_server.model.Language;
import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionCSV;
import pl.prawko.prawko_server.model.QuestionTranslation;
import pl.prawko.prawko_server.model.QuestionType;

import java.util.List;
import java.util.Map;

import static pl.prawko.prawko_server.test_utils.CategoryTestData.CATEGORIES_AB;
import static pl.prawko.prawko_server.test_utils.CategoryTestData.CATEGORY_PT;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.ENG;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.GER;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.POL;

public class TestDataUtil {


    private static final String
            SPECIAL_QUESTION_CONTENT_POL = "Jak często należy obracać poszkodowanego nieurazowego na drugi bok po ułożeniu go w pozycji bezpiecznej?";
    private static final String
            SPECIAL_QUESTION_CONTENT_ENG = "How often should you turn a non-traumatic victim to the other side after laying him in the recovery position?";
    private static final String
            SPECIAL_QUESTION_CONTENT_GER = "Wie oft soll man einen symptomlosen Betroffenen auf die andere Körperseite nach dem Legen in stabiler Seitenlage drehen?";
    private static final String ANSWER_A_POL = "Co 60 minut.";
    private static final String ANSWER_B_POL = "Co 30 minut.";
    private static final String ANSWER_C_POL = "Co 15 minut.";
    private static final String ANSWER_A_ENG = "Every 60 minutes.";
    private static final String ANSWER_B_ENG = "Every 30 minutes.";
    private static final String ANSWER_C_ENG = "Every 15 minutes.";
    private static final String ANSWER_A_GER = "jede 60 Minuten";
    private static final String ANSWER_B_GER = "jede 30 Minuten";
    private static final String ANSWER_C_GER = "jede 15 Minuten";
    public static final List<String> BASIC_ANSWERS = List.of(
            ANSWER_A_POL, ANSWER_B_POL, ANSWER_C_POL,
            ANSWER_A_ENG, ANSWER_B_ENG, ANSWER_C_ENG,
            ANSWER_A_GER, ANSWER_B_GER, ANSWER_C_GER
    );
    private static final String ANSWER_YES_POL = "Tak.";
    private static final String ANSWER_NO_POL = "Nie.";
    private static final String ANSWER_YES_ENG = "Yes.";
    private static final String ANSWER_NO_ENG = "No.";
    private static final String ANSWER_YES_GER = "Ja.";
    private static final String ANSWER_NO_GER = "Nein.";
    public static final List<String> SPECIAL_ANSWERS = List.of(
            ANSWER_YES_POL, ANSWER_NO_POL,
            ANSWER_YES_ENG, ANSWER_NO_ENG,
            ANSWER_YES_GER, ANSWER_NO_GER
    );
    private static final String BASIC_QUESTION_CONTENT_POL = "Czy w przedstawionej sytuacji masz prawo - mimo podawanego sygnału - skręcić w prawo?";
    private static final String BASIC_QUESTION_CONTENT_ENG = "Are you allowed in this situation to turn right despite the light displayed?";
    private static final String BASIC_QUESTION_CONTENT_GER = "Darfst du in der dargestellten Situation - trotz des gegebenen Signals - rechts abbiegen?";

    public static final QuestionCSV SPECIAL_QUESTION_CSV = new QuestionCSV(
            "PD10(3)",
            2143,
            SPECIAL_QUESTION_CONTENT_POL,
            ANSWER_A_POL,
            ANSWER_B_POL,
            ANSWER_C_POL,
            SPECIAL_QUESTION_CONTENT_ENG,
            ANSWER_A_ENG,
            ANSWER_B_ENG,
            ANSWER_C_ENG,
            SPECIAL_QUESTION_CONTENT_GER,
            ANSWER_A_GER,
            ANSWER_B_GER,
            ANSWER_C_GER,
            'B',
            "R_101org.jpg",
            "SPECJALISTYCZNY",
            2,
            "PT"
    );


    public static final Question SPECIAL_QUESTION = new Question()
            .withName("PD10(3)")
            .withId(2143)
            .withMedia("R_101org.jpg")
            .withType(QuestionType.SPECIAL)
            .withValue(2)
            .withCategories
                    (List.of(CATEGORY_PT))
            .withTranslations(
                    specialQuestionTranslations())
            .withAnswers(
                    List.of(
                            createSpecialAnswer('A', false),
                            createSpecialAnswer('B', true),
                            createSpecialAnswer('C', false)
                    ));


    public static final QuestionCSV BASIC_QUESTION_CSV = new QuestionCSV(
            "W9(2)",
            110,
            BASIC_QUESTION_CONTENT_POL,
            "",
            "",
            "",
            BASIC_QUESTION_CONTENT_ENG,
            "",
            "",
            "",
            BASIC_QUESTION_CONTENT_GER,
            "",
            "",
            "",
            'N',
            "AK_D11_45org.wmv",
            "PODSTAWOWY",
            3,
            "A,B"
    );


    public static Question BASIC_QUESTION = new Question()
            .withName("W9(2)")
            .withId(110)
            .withMedia("AK_D11_45org.webm")
            .withType(QuestionType.BASIC)
            .withValue(3)
            .withCategories(
                    CATEGORIES_AB)
            .withTranslations(
                    basicQuestionTranslations())
            .withAnswers(
                    List.of(
                            createBasicAnswer('Y', false),
                            createBasicAnswer('N', true)
                    ));


    private static Answer createSpecialAnswer(final char label,
                                              final boolean correct) {
        final var answer = new Answer()
                .withLabel(label)
                .withCorrect(correct)
                .withQuestion(SPECIAL_QUESTION);
        answer.setTranslations(
                specialAnswerTranslations(answer));
        return answer;
    }

    private static Answer createBasicAnswer(final char label,
                                            final boolean correct) {
        final var answer = new Answer()
                .withLabel(label)
                .withCorrect(correct)
                .withQuestion(BASIC_QUESTION);
        answer.setTranslations(
                basicAnswerTranslations(answer));
        return answer;
    }

    private static List<AnswerTranslation> basicAnswerTranslations(final Answer answer) {
        final Map<Language, List<String>> translationsByLanguage = Map.of(
                POL, List.of(
                        ANSWER_YES_POL,
                        ANSWER_NO_POL),
                ENG, List.of(
                        ANSWER_YES_ENG,
                        ANSWER_NO_ENG),
                GER, List.of(
                        ANSWER_YES_GER,
                        ANSWER_NO_GER)
        );
        return translationsByLanguage.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .map(content -> new AnswerTranslation()
                                .withAnswer(answer)
                                .withLanguage(entry.getKey())
                                .withContent(content)))
                .toList();
    }

    private static List<AnswerTranslation> specialAnswerTranslations(final Answer answer) {
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

    private static QuestionTranslation createQuestionTranslation(final Question question,
                                                                 final Language language,
                                                                 final String content) {
        return new QuestionTranslation()
                .withContent(content)
                .withLanguage(language)
                .withQuestion(question);
    }

    private static List<QuestionTranslation> basicQuestionTranslations() {
        return List.of(
                createQuestionTranslation(BASIC_QUESTION, POL, BASIC_QUESTION_CONTENT_POL),
                createQuestionTranslation(BASIC_QUESTION, ENG, BASIC_QUESTION_CONTENT_ENG),
                createQuestionTranslation(BASIC_QUESTION, GER, BASIC_QUESTION_CONTENT_GER)
        );
    }

    private static List<QuestionTranslation> specialQuestionTranslations() {
        return List.of(
                createQuestionTranslation(SPECIAL_QUESTION, POL, SPECIAL_QUESTION_CONTENT_POL),
                createQuestionTranslation(SPECIAL_QUESTION, ENG, SPECIAL_QUESTION_CONTENT_ENG),
                createQuestionTranslation(SPECIAL_QUESTION, GER, SPECIAL_QUESTION_CONTENT_GER)
        );
    }

}
