package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.Answer;

import java.util.List;

import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.getAnswerTranslationsA;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.getAnswerTranslationsB;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.getAnswerTranslationsC;

public class AnswerTestData {

    private static final Answer ANSWER_A = new Answer()
            .setCorrect(false)
            .setTranslations(getAnswerTranslationsA());

    private static final Answer ANSWER_B = new Answer()
            .setCorrect(true)
            .setTranslations(getAnswerTranslationsB());

    private static final Answer ANSWER_C = new Answer()
            .setCorrect(false)
            .setTranslations(getAnswerTranslationsC());

    private static final Answer ANSWER_Y = new Answer()
            .setCorrect(false);

    private static final Answer ANSWER_N = new Answer()
            .setCorrect(true);

    public static List<Answer> getSpecialAnswers() {
        return List.of(ANSWER_A, ANSWER_B, ANSWER_C);
    }

    public static List<Answer> getBasicAnswers() {
        return List.of(ANSWER_N, ANSWER_Y);
    }

}
