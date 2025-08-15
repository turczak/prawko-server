package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.Answer;

import java.util.List;

import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_A_TRANSLATIONS;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_B_TRANSLATIONS;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_C_TRANSLATIONS;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_N_TRANSLATIONS;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_Y_TRANSLATIONS;
import static pl.prawko.prawko_server.test_utils.QuestionTestData.BASIC_QUESTION;
import static pl.prawko.prawko_server.test_utils.QuestionTestData.SPECIAL_QUESTION;

public class AnswerTestData {

    static final Answer ANSWER_A = new Answer()
            .setLabel('A')
            .setCorrect(false)
            .setQuestion(SPECIAL_QUESTION)
            .setTranslations(ANSWER_A_TRANSLATIONS());

    static final Answer ANSWER_B = new Answer()
            .setLabel('B')
            .setCorrect(true)
            .setQuestion(SPECIAL_QUESTION)
            .setTranslations(ANSWER_B_TRANSLATIONS());

    static final Answer ANSWER_C = new Answer()
            .setLabel('C')
            .setCorrect(false)
            .setQuestion(SPECIAL_QUESTION)
            .setTranslations(ANSWER_C_TRANSLATIONS());

    static final Answer ANSWER_Y = new Answer()
            .setLabel('Y')
            .setCorrect(false)
            .setQuestion(BASIC_QUESTION)
            .setTranslations(ANSWER_Y_TRANSLATIONS());

    static final Answer ANSWER_N = new Answer()
            .setLabel('N')
            .setCorrect(true)
            .setQuestion(BASIC_QUESTION)
            .setTranslations(ANSWER_N_TRANSLATIONS());

    public static final List<Answer> SPECIAL_ANSWERS = List.of(
            ANSWER_A,
            ANSWER_B,
            ANSWER_C
    );

    public static final List<Answer> BASIC_ANSWERS = List.of(
            ANSWER_Y, ANSWER_N
    );

}
