package pl.prawko.prawko_server.util;

import pl.prawko.prawko_server.model.Answer;
import pl.prawko.prawko_server.model.AnswerTranslation;
import pl.prawko.prawko_server.model.Category;
import pl.prawko.prawko_server.model.Language;
import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionCSV;
import pl.prawko.prawko_server.model.QuestionTranslation;
import pl.prawko.prawko_server.model.QuestionType;

import java.util.List;
import java.util.Map;

public class TestDataUtil {

    public static final Language pol = new Language()
            .withId(1L)
            .withName("polish")
            .withCode("pol");
    public static final Language eng = new Language()
            .withId(2L)
            .withName("english")
            .withCode("eng");
    public static final Language ger = new Language()
            .withId(3L)
            .withName("german")
            .withCode("ger");
    public static final Category category = new Category()
            .withId(12L)
            .withName("PT");
    private static final String
            questionContent_pol = "Jak często należy obracać poszkodowanego nieurazowego na drugi bok po ułożeniu go w pozycji bezpiecznej?";
    private static final String
            questionContent_eng = "How often should you turn a non-traumatic victim to the other side after laying him in the recovery position?";
    private static final String
            questionContent_ger = "Wie oft soll man einen symptomlosen Betroffenen auf die andere Körperseite nach dem Legen in stabiler Seitenlage drehen?";
    private static final String answer_A_pol = "Co 60 minut.";
    private static final String answer_B_pol = "Co 30 minut.";
    private static final String answer_C_pol = "Co 15 minut.";
    private static final String answer_A_eng = "Every 60 minutes.";
    private static final String answer_B_eng = "Every 30 minutes.";
    private static final String answer_C_eng = "Every 15 minutes.";
    private static final String answer_A_ger = "jede 60 Minuten";
    private static final String answer_B_ger = "jede 30 Minuten";
    private static final String answer_C_ger = "jede 15 Minuten";

    public static final QuestionCSV QUESTION_CSV = new QuestionCSV(
            "PD10(3)",
            2143,
            questionContent_pol,
            answer_A_pol,
            answer_B_pol,
            answer_C_pol,
            questionContent_eng,
            answer_A_eng,
            answer_B_eng,
            answer_C_eng,
            questionContent_ger,
            answer_A_ger,
            answer_B_ger,
            answer_C_ger,
            'B',
            "R_101org.jpg",
            "SPECJALISTYCZNY",
            2,
            "PT"
    );

    public static final Question question = new Question()
            .withName("PD10(3)")
            .withId(2143)
            .withMedia("R_101org.jpg")
            .withType(QuestionType.SPECIAL)
            .withValue(2)
            .withCategories
                    (List.of(category))
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
                .withQuestion(question);
        answer.setTranslations(
                answerTranslations(answer));
        return answer;
    }

    private static List<AnswerTranslation> answerTranslations(final Answer answer) {
        final Map<Language, List<String>> translationsByLanguage = Map.of(
                pol, List.of(
                        answer_A_pol,
                        answer_B_pol,
                        answer_C_pol),
                eng, List.of(
                        answer_A_eng,
                        answer_B_eng,
                        answer_C_eng),
                ger, List.of(
                        answer_A_ger,
                        answer_B_ger,
                        answer_C_ger)
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
                .withQuestion(question);
    }

    private static List<QuestionTranslation> questionTranslations() {
        return Map.of(
                        pol, questionContent_pol,
                        eng, questionContent_eng,
                        ger, questionContent_ger)
                .entrySet().stream()
                .map(entry ->
                        createQuestionTranslation(
                                entry.getKey(),
                                entry.getValue()))
                .toList();
    }

}
