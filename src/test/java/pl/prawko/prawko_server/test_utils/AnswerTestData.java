package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.Answer;

import java.util.List;

import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.getAnswerTranslationsA;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.getAnswerTranslationsB;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.getAnswerTranslationsC;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.getAnswerTranslationsN;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.getAnswerTranslationsY;
import static pl.prawko.prawko_server.test_utils.QuestionTestData.BASIC_QUESTION;
import static pl.prawko.prawko_server.test_utils.QuestionTestData.SPECIAL_QUESTION;

public class AnswerTestData {

    static final Answer ANSWER_A = new Answer()
            .setLabel('A')
            .setCorrect(false)
            .setQuestion(SPECIAL_QUESTION)
            .setTranslations(getAnswerTranslationsA());

    static final Answer ANSWER_B = new Answer()
            .setLabel('B')
            .setCorrect(true)
            .setQuestion(SPECIAL_QUESTION)
            .setTranslations(getAnswerTranslationsB());

    static final Answer ANSWER_C = new Answer()
            .setLabel('C')
            .setCorrect(false)
            .setQuestion(SPECIAL_QUESTION)
            .setTranslations(getAnswerTranslationsC());

    static final Answer ANSWER_Y = new Answer()
            .setLabel('Y')
            .setCorrect(false)
            .setQuestion(BASIC_QUESTION)
            .setTranslations(getAnswerTranslationsY());

    static final Answer ANSWER_N = new Answer()
            .setLabel('N')
            .setCorrect(true)
            .setQuestion(BASIC_QUESTION)
            .setTranslations(getAnswerTranslationsN());

    public static final List<Answer> SPECIAL_ANSWERS = List.of(
            ANSWER_A,
            ANSWER_B,
            ANSWER_C
    );

    public static final List<Answer> BASIC_ANSWERS = List.of(
            ANSWER_N, ANSWER_Y
    );

}
