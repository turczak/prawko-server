package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.QuestionTranslation;

import java.util.List;

import static pl.prawko.prawko_server.test_utils.LanguageTestData.ENG;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.GER;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.POL;

public class QuestionTranslationTestData {

    static final QuestionTranslation SPECIAL_QUESTION_CONTENT_POL = new QuestionTranslation()
            .setContent("Jak często należy obracać poszkodowanego nieurazowego na drugi bok po ułożeniu go w pozycji bezpiecznej?")
            .setLanguage(POL);

    static final QuestionTranslation SPECIAL_QUESTION_CONTENT_ENG = new QuestionTranslation()
            .setContent("How often should you turn a non-traumatic victim to the other side after laying him in the recovery position?")
            .setLanguage(ENG);

    static final QuestionTranslation SPECIAL_QUESTION_CONTENT_GER = new QuestionTranslation()
            .setContent("Wie oft soll man einen symptomlosen Betroffenen auf die andere Körperseite nach dem Legen in stabiler Seitenlage drehen?")
            .setLanguage(GER);

    static List<QuestionTranslation> SPECIAL_QUESTION_TRANSLATIONS() {
        return List.of(
                SPECIAL_QUESTION_CONTENT_POL,
                SPECIAL_QUESTION_CONTENT_ENG,
                SPECIAL_QUESTION_CONTENT_GER
        );
    }

    static final QuestionTranslation BASIC_QUESTION_CONTENT_POL = new QuestionTranslation()
            .setContent("Czy w przedstawionej sytuacji masz prawo - mimo podawanego sygnału - skręcić w prawo?")
            .setLanguage(POL);

    static final QuestionTranslation BASIC_QUESTION_CONTENT_ENG = new QuestionTranslation()
            .setContent("Are you allowed in this situation to turn right despite the light displayed?")
            .setLanguage(ENG);

    static final QuestionTranslation BASIC_QUESTION_CONTENT_GER = new QuestionTranslation()
            .setContent("Darfst du in der dargestellten Situation - trotz des gegebenen Signals - rechts abbiegen?")
            .setLanguage(GER);

    static List<QuestionTranslation> BASIC_QUESTION_TRANSLATIONS() {
        return List.of(
                BASIC_QUESTION_CONTENT_POL,
                BASIC_QUESTION_CONTENT_ENG,
                BASIC_QUESTION_CONTENT_GER
        );
    }

}
