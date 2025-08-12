package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.Language;
import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionType;

import java.util.List;
import java.util.Map;

import static pl.prawko.prawko_server.test_utils.AnswerTestData.BASIC_ANSWERS;
import static pl.prawko.prawko_server.test_utils.AnswerTestData.BASIC_ANSWER_TRANSLATIONS;
import static pl.prawko.prawko_server.test_utils.AnswerTestData.SPECIAL_ANSWERS;
import static pl.prawko.prawko_server.test_utils.AnswerTestData.SPECIAL_ANSWER_TRANSLATIONS;
import static pl.prawko.prawko_server.test_utils.AnswerTestData.createAnswers;
import static pl.prawko.prawko_server.test_utils.CategoryTestData.CATEGORIES_AB;
import static pl.prawko.prawko_server.test_utils.CategoryTestData.CATEGORY_PT;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.ENG;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.GER;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.POL;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationsTestData.createTranslations;

public class QuestionTestData {

    public static final Map<Language, String> BASIC_QUESTION_CONTENT = Map.of(
            POL, "Czy w przedstawionej sytuacji masz prawo - mimo podawanego sygnału - skręcić w prawo?",
            ENG, "Are you allowed in this situation to turn right despite the light displayed?",
            GER, "Darfst du in der dargestellten Situation - trotz des gegebenen Signals - rechts abbiegen?"
    );
    public static final Map<Language, String> SPECIAL_QUESTION_CONTENT = Map.of(
            POL, "Jak często należy obracać poszkodowanego nieurazowego na drugi bok po ułożeniu go w pozycji bezpiecznej?",
            ENG, "How often should you turn a non-traumatic victim to the other side after laying him in the recovery position?",
            GER, "Wie oft soll man einen symptomlosen Betroffenen auf die andere Körperseite nach dem Legen in stabiler Seitenlage drehen?"

    );

    public static Question BASIC_QUESTION;

    static {
        BASIC_QUESTION = new Question()
                .withName("W9(2)")
                .withId(110)
                .withMedia("AK_D11_45org.webm")
                .withType(QuestionType.BASIC)
                .withPoints(3)
                .withCategories(
                        CATEGORIES_AB);
        BASIC_QUESTION.setTranslations(
                createTranslations(BASIC_QUESTION, BASIC_QUESTION_CONTENT));
        BASIC_QUESTION.setAnswers(
                createAnswers(
                        BASIC_QUESTION,
                        BASIC_ANSWERS,
                        BASIC_ANSWER_TRANSLATIONS));
    }

    public static final Question SPECIAL_QUESTION;

    static {
        SPECIAL_QUESTION = new Question()
                .withName("PD10(3)")
                .withId(2143)
                .withMedia("R_101org.jpg")
                .withType(QuestionType.SPECIAL)
                .withPoints(2)
                .withCategories
                        (List.of(CATEGORY_PT));
        SPECIAL_QUESTION.setTranslations(
                createTranslations(SPECIAL_QUESTION, SPECIAL_QUESTION_CONTENT));
        SPECIAL_QUESTION.setAnswers(
                createAnswers(
                        SPECIAL_QUESTION,
                        SPECIAL_ANSWERS,
                        SPECIAL_ANSWER_TRANSLATIONS));
    }

}
