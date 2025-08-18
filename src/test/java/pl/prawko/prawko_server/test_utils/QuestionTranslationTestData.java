package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.QuestionTranslation;

import java.util.List;

import static pl.prawko.prawko_server.test_utils.LanguageTestData.DE;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.EN;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.PL;

public class QuestionTranslationTestData {

    static final QuestionTranslation SPECIAL_QUESTION_CONTENT_PL = new QuestionTranslation()
            .setContent("Jak często należy obracać poszkodowanego nieurazowego na drugi bok po ułożeniu go w pozycji bezpiecznej?")
            .setLanguage(PL);

    static final QuestionTranslation SPECIAL_QUESTION_CONTENT_EN = new QuestionTranslation()
            .setContent("How often should you turn a non-traumatic victim to the other side after laying him in the recovery position?")
            .setLanguage(EN);

    static final QuestionTranslation SPECIAL_QUESTION_CONTENT_DE = new QuestionTranslation()
            .setContent("Wie oft soll man einen symptomlosen Betroffenen auf die andere Körperseite nach dem Legen in stabiler Seitenlage drehen?")
            .setLanguage(DE);

    static final QuestionTranslation BASIC_QUESTION_CONTENT_PL = new QuestionTranslation()
            .setContent("Czy w przedstawionej sytuacji masz prawo - mimo podawanego sygnału - skręcić w prawo?")
            .setLanguage(PL);

    static final QuestionTranslation BASIC_QUESTION_CONTENT_EN = new QuestionTranslation()
            .setContent("Are you allowed in this situation to turn right despite the light displayed?")
            .setLanguage(EN);

    static final QuestionTranslation BASIC_QUESTION_CONTENT_DE = new QuestionTranslation()
            .setContent("Darfst du in der dargestellten Situation - trotz des gegebenen Signals - rechts abbiegen?")
            .setLanguage(DE);

    static List<QuestionTranslation> getSpecialQuestionTranslations() {
        return List.of(
                SPECIAL_QUESTION_CONTENT_PL,
                SPECIAL_QUESTION_CONTENT_EN,
                SPECIAL_QUESTION_CONTENT_DE
        );
    }

    static List<QuestionTranslation> getBasicQuestionTranslations() {
        return List.of(
                BASIC_QUESTION_CONTENT_PL,
                BASIC_QUESTION_CONTENT_EN,
                BASIC_QUESTION_CONTENT_DE
        );
    }

}
