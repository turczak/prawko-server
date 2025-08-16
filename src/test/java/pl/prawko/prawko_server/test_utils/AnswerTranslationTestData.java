package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.AnswerTranslation;

import java.util.List;

import static pl.prawko.prawko_server.test_utils.LanguageTestData.ENG;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.GER;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.POL;

public class AnswerTranslationTestData {

    static final AnswerTranslation ANSWER_A_POL = new AnswerTranslation()
            .setLanguage(POL)
            .setContent("Co 60 minut.");

    static final AnswerTranslation ANSWER_A_ENG = new AnswerTranslation()
            .setLanguage(ENG)
            .setContent("Every 60 minutes.");

    static final AnswerTranslation ANSWER_A_GER = new AnswerTranslation()
            .setLanguage(GER)
            .setContent("jede 60 Minuten");

    static final AnswerTranslation ANSWER_B_POL = new AnswerTranslation()
            .setLanguage(POL)
            .setContent("Co 30 minut.");

    static final AnswerTranslation ANSWER_B_ENG = new AnswerTranslation()
            .setLanguage(ENG)
            .setContent("Every 30 minutes.");

    static final AnswerTranslation ANSWER_B_GER = new AnswerTranslation()
            .setLanguage(GER)
            .setContent("jede 30 Minuten");

    static final AnswerTranslation ANSWER_C_POL = new AnswerTranslation()
            .setLanguage(POL)
            .setContent("Co 15 minut.");

    static final AnswerTranslation ANSWER_C_ENG = new AnswerTranslation()
            .setLanguage(ENG)
            .setContent("Every 15 minutes.");

    static final AnswerTranslation ANSWER_C_GER = new AnswerTranslation()
            .setLanguage(GER)
            .setContent("jede 15 Minuten");

    private static final AnswerTranslation ANSWER_Y_POL = new AnswerTranslation()
            .setLanguage(POL)
            .setContent("Tak");

    private static final AnswerTranslation ANSWER_Y_ENG = new AnswerTranslation()
            .setLanguage(ENG)
            .setContent("Yes");

    private static final AnswerTranslation ANSWER_Y_GER = new AnswerTranslation()
            .setLanguage(GER)
            .setContent("Ja");

    private static final AnswerTranslation ANSWER_N_POL = new AnswerTranslation()
            .setLanguage(POL)
            .setContent("Nie");

    private static final AnswerTranslation ANSWER_N_ENG = new AnswerTranslation()
            .setLanguage(ENG)
            .setContent("No");

    private static final AnswerTranslation ANSWER_N_GER = new AnswerTranslation()
            .setLanguage(GER)
            .setContent("Nein");

    static List<AnswerTranslation> getAnswerTranslationsA() {
        return List.of(
                ANSWER_A_POL,
                ANSWER_A_ENG,
                ANSWER_A_GER
        );
    }

    static List<AnswerTranslation> getAnswerTranslationsB() {
        return List.of(
                ANSWER_B_POL,
                ANSWER_B_ENG,
                ANSWER_B_GER
        );
    }

    static List<AnswerTranslation> getAnswerTranslationsC() {
        return List.of(
                ANSWER_C_POL,
                ANSWER_C_ENG,
                ANSWER_C_GER
        );
    }

    static List<AnswerTranslation> getAnswerTranslationsY() {
        return List.of(
                ANSWER_Y_POL,
                ANSWER_Y_ENG,
                ANSWER_Y_GER
        );
    }

    static List<AnswerTranslation> getAnswerTranslationsN() {
        return List.of(
                ANSWER_N_POL,
                ANSWER_N_ENG,
                ANSWER_N_GER
        );
    }

}
