package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.dto.AnswerDto;
import pl.prawko.prawko_server.model.Answer;

import java.util.List;

import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.getAnswerTranslationsA;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.getAnswerTranslationsB;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.getAnswerTranslationsC;

public class AnswerTestData {

    public static final Answer ANSWER_A = new Answer()
            .setQuestion(QuestionTestData.SPECIAL_QUESTION)
            .setCorrect(false)
            .setTranslations(getAnswerTranslationsA());

    public static final Answer ANSWER_B = new Answer()
            .setCorrect(true)
            .setTranslations(getAnswerTranslationsB());

    public static final Answer ANSWER_C = new Answer()
            .setCorrect(false)
            .setTranslations(getAnswerTranslationsC());

    public static final Answer ANSWER_Y = new Answer()
            .setCorrect(false);

    public static final Answer ANSWER_N = new Answer()
            .setCorrect(true);

    public static final AnswerDto ANSWER_A_MAPPED = new AnswerDto(0L, 2143, false, AnswerTranslationTestData.getTranslationsADtos());

    public static List<Answer> getSpecialAnswers() {
        return List.of(ANSWER_A, ANSWER_B, ANSWER_C);
    }

    public static List<Answer> getBasicAnswers() {
        return List.of(ANSWER_N, ANSWER_Y);
    }

}
