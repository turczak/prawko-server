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

    private static final Map<Language, List<String>> SPECIAL_ANSWER_TRANSLATIONS = Map.of(
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
    private static final Map<Language, List<String>> BASIC_ANSWER_TRANSLATIONS = Map.of(
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
    private static final Map<Language, String> BASIC_QUESTION_CONTENT = Map.of(
            POL, "Czy w przedstawionej sytuacji masz prawo - mimo podawanego sygnału - skręcić w prawo?",
            ENG, "Are you allowed in this situation to turn right despite the light displayed?",
            GER, "Darfst du in der dargestellten Situation - trotz des gegebenen Signals - rechts abbiegen?"
    );
    private static final Map<Language, String> SPECIAL_QUESTION_CONTENT = Map.of(
            POL, "Jak często należy obracać poszkodowanego nieurazowego na drugi bok po ułożeniu go w pozycji bezpiecznej?",
            ENG, "How often should you turn a non-traumatic victim to the other side after laying him in the recovery position?",
            GER, "Wie oft soll man einen symptomlosen Betroffenen auf die andere Körperseite nach dem Legen in stabiler Seitenlage drehen?"

    );
    public static final QuestionCSV SPECIAL_QUESTION_CSV = new QuestionCSV(
            "PD10(3)",
            2143,
            SPECIAL_QUESTION_CONTENT.get(POL),
            SPECIAL_ANSWER_TRANSLATIONS.get(POL).getFirst(),
            SPECIAL_ANSWER_TRANSLATIONS.get(POL).get(1),
            SPECIAL_ANSWER_TRANSLATIONS.get(POL).get(2),
            SPECIAL_QUESTION_CONTENT.get(ENG),
            SPECIAL_ANSWER_TRANSLATIONS.get(ENG).getFirst(),
            SPECIAL_ANSWER_TRANSLATIONS.get(ENG).get(1),
            SPECIAL_ANSWER_TRANSLATIONS.get(ENG).get(2),
            SPECIAL_QUESTION_CONTENT.get(GER),
            SPECIAL_ANSWER_TRANSLATIONS.get(GER).getFirst(),
            SPECIAL_ANSWER_TRANSLATIONS.get(GER).get(1),
            SPECIAL_ANSWER_TRANSLATIONS.get(GER).get(2),
            'B',
            "R_101org.jpg",
            "SPECJALISTYCZNY",
            2,
            "PT"
    );
    public static final Question SPECIAL_QUESTION;

    static {
        SPECIAL_QUESTION = new Question()
                .withName("PD10(3)")
                .withId(2143)
                .withMedia("R_101org.jpg")
                .withType(QuestionType.SPECIAL)
                .withValue(2)
                .withCategories
                        (List.of(CATEGORY_PT));
        SPECIAL_QUESTION.setTranslations(
                createTranslations(SPECIAL_QUESTION, SPECIAL_QUESTION_CONTENT));
        SPECIAL_QUESTION.setAnswers(
                List.of(
                        createAnswer('A', false, SPECIAL_ANSWER_TRANSLATIONS),
                        createAnswer('B', true, SPECIAL_ANSWER_TRANSLATIONS),
                        createAnswer('C', false, SPECIAL_ANSWER_TRANSLATIONS)));
    }

    public static final QuestionCSV BASIC_QUESTION_CSV = new QuestionCSV(
            "W9(2)",
            110,
            BASIC_QUESTION_CONTENT.get(POL),
            "",
            "",
            "",
            BASIC_QUESTION_CONTENT.get(ENG),
            "",
            "",
            "",
            BASIC_QUESTION_CONTENT.get(GER),
            "",
            "",
            "",
            'N',
            "AK_D11_45org.wmv",
            "PODSTAWOWY",
            3,
            "A,B"
    );
    public static Question BASIC_QUESTION;

    static {
        BASIC_QUESTION = new Question()
                .withName("W9(2)")
                .withId(110)
                .withMedia("AK_D11_45org.webm")
                .withType(QuestionType.BASIC)
                .withValue(3)
                .withCategories(
                        CATEGORIES_AB);
        BASIC_QUESTION.setTranslations(
                createTranslations(BASIC_QUESTION, BASIC_QUESTION_CONTENT));
        BASIC_QUESTION.setAnswers(
                List.of(
                        createAnswer('Y', false, BASIC_ANSWER_TRANSLATIONS),
                        createAnswer('N', true, BASIC_ANSWER_TRANSLATIONS)));
    }

    private static Answer createAnswer(final char label,
                                       final boolean correct,
                                       final Map<Language, List<String>> map) {
        final var question = label == 'Y' || label == 'N'
                ? BASIC_QUESTION
                : SPECIAL_QUESTION;
        final var index = switch (label) {
            case 'A', 'Y' -> 0;
            case 'B', 'N' -> 1;
            case 'C' -> 2;
            default -> throw new IllegalStateException("Unexpected value: " + label);
        };
        final var answer = new Answer()
                .withLabel(label)
                .withCorrect(correct)
                .withQuestion(question);
        answer.setTranslations(
                map.entrySet().stream()
                        .map(e -> new AnswerTranslation()
                                .withAnswer(answer)
                                .withLanguage(e.getKey())
                                .withContent(e.getValue().get(index)))
                        .toList()
        );
        return answer;
    }

    private static List<QuestionTranslation> createTranslations(final Question question,
                                                                final Map<Language, String> map) {
        return map.entrySet().stream()
                .map(entry -> new QuestionTranslation()
                        .withQuestion(question)
                        .withLanguage(entry.getKey())
                        .withContent(entry.getValue()))
                .toList();
    }

}
