package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.Answer;

import java.util.List;

import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.getAnswerTranslationsA;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.getAnswerTranslationsB;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.getAnswerTranslationsC;
import static pl.prawko.prawko_server.test_utils.QuestionTestData.BASIC_QUESTION;
import static pl.prawko.prawko_server.test_utils.QuestionTestData.SPECIAL_QUESTION;

public class AnswerTestData {

    static final Answer ANSWER_A = new Answer()
            .setCorrect(false)
            .setQuestion(SPECIAL_QUESTION)
            .setTranslations(getAnswerTranslationsA());

    static final Answer ANSWER_B = new Answer()
            .setCorrect(true)
            .setQuestion(SPECIAL_QUESTION)
            .setTranslations(getAnswerTranslationsB());

    static final Answer ANSWER_C = new Answer()
            .setCorrect(false)
            .setQuestion(SPECIAL_QUESTION)
            .setTranslations(getAnswerTranslationsC());

    static final Answer ANSWER_Y = new Answer()
            .setCorrect(false)
            .setQuestion(BASIC_QUESTION);

    static final Answer ANSWER_N = new Answer()
            .setCorrect(true)
            .setQuestion(BASIC_QUESTION);

    public static final List<Answer> SPECIAL_ANSWERS = List.of(
            ANSWER_A,
            ANSWER_B,
            ANSWER_C
    );

    public static final List<Answer> BASIC_ANSWERS = List.of(
            ANSWER_N, ANSWER_Y
    );

}
