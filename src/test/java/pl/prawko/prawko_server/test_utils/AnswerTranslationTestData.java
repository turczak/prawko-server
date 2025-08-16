package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.AnswerTranslation;

import java.util.List;

import static pl.prawko.prawko_server.test_utils.LanguageTestData.DE;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.EN;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.PL;

public class AnswerTranslationTestData {

    static final AnswerTranslation ANSWER_A_PL = new AnswerTranslation()
            .setLanguage(PL)
            .setContent("Co 60 minut.");

    static final AnswerTranslation ANSWER_A_EN = new AnswerTranslation()
            .setLanguage(EN)
            .setContent("Every 60 minutes.");

    static final AnswerTranslation ANSWER_A_DE = new AnswerTranslation()
            .setLanguage(DE)
            .setContent("jede 60 Minuten");

    static final AnswerTranslation ANSWER_B_PL = new AnswerTranslation()
            .setLanguage(PL)
            .setContent("Co 30 minut.");

    static final AnswerTranslation ANSWER_B_EN = new AnswerTranslation()
            .setLanguage(EN)
            .setContent("Every 30 minutes.");

    static final AnswerTranslation ANSWER_B_DE = new AnswerTranslation()
            .setLanguage(DE)
            .setContent("jede 30 Minuten");

    static final AnswerTranslation ANSWER_C_PL = new AnswerTranslation()
            .setLanguage(PL)
            .setContent("Co 15 minut.");

    static final AnswerTranslation ANSWER_C_EN = new AnswerTranslation()
            .setLanguage(EN)
            .setContent("Every 15 minutes.");

    static final AnswerTranslation ANSWER_C_DE = new AnswerTranslation()
            .setLanguage(DE)
            .setContent("jede 15 Minuten");

    private static final AnswerTranslation ANSWER_Y_PL = new AnswerTranslation()
            .setLanguage(PL)
            .setContent("Tak");

    private static final AnswerTranslation ANSWER_Y_EN = new AnswerTranslation()
            .setLanguage(EN)
            .setContent("Yes");

    private static final AnswerTranslation ANSWER_Y_DE = new AnswerTranslation()
            .setLanguage(DE)
            .setContent("Ja");

    private static final AnswerTranslation ANSWER_N_PL = new AnswerTranslation()
            .setLanguage(PL)
            .setContent("Nie");

    private static final AnswerTranslation ANSWER_N_EN = new AnswerTranslation()
            .setLanguage(EN)
            .setContent("No");

    private static final AnswerTranslation ANSWER_N_DE = new AnswerTranslation()
            .setLanguage(DE)
            .setContent("Nein");

    static List<AnswerTranslation> getAnswerTranslationsA() {
        return List.of(
                ANSWER_A_PL,
                ANSWER_A_EN,
                ANSWER_A_DE
        );
    }

    static List<AnswerTranslation> getAnswerTranslationsB() {
        return List.of(
                ANSWER_B_PL,
                ANSWER_B_EN,
                ANSWER_B_DE
        );
    }

    static List<AnswerTranslation> getAnswerTranslationsC() {
        return List.of(
                ANSWER_C_PL,
                ANSWER_C_EN,
                ANSWER_C_DE
        );
    }

    static List<AnswerTranslation> getAnswerTranslationsY() {
        return List.of(
                ANSWER_Y_PL,
                ANSWER_Y_EN,
                ANSWER_Y_DE
        );
    }

    static List<AnswerTranslation> getAnswerTranslationsN() {
        return List.of(
                ANSWER_N_PL,
                ANSWER_N_EN,
                ANSWER_N_DE
        );
    }

}
