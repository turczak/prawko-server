package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.QuestionCSV;

import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_A_DE;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_A_EN;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_A_PL;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_B_DE;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_B_EN;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_B_PL;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_C_DE;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_C_EN;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_C_PL;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.BASIC_QUESTION_CONTENT_DE;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.BASIC_QUESTION_CONTENT_EN;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.BASIC_QUESTION_CONTENT_PL;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.SPECIAL_QUESTION_CONTENT_DE;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.SPECIAL_QUESTION_CONTENT_EN;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.SPECIAL_QUESTION_CONTENT_PL;

public class QuestionCSVTestData {

    public static final QuestionCSV SPECIAL_QUESTION_CSV = new QuestionCSV(
            "PD10(3)",
            2143,
            SPECIAL_QUESTION_CONTENT_PL.getContent(),
            ANSWER_A_PL.getContent(),
            ANSWER_B_PL.getContent(),
            ANSWER_C_PL.getContent(),
            SPECIAL_QUESTION_CONTENT_EN.getContent(),
            ANSWER_A_EN.getContent(),
            ANSWER_B_EN.getContent(),
            ANSWER_C_EN.getContent(),
            SPECIAL_QUESTION_CONTENT_DE.getContent(),
            ANSWER_A_DE.getContent(),
            ANSWER_B_DE.getContent(),
            ANSWER_C_DE.getContent(),
            'B',
            "R_101org.jpg",
            "SPECJALISTYCZNY",
            2,
            "PT"
    );

    public static final QuestionCSV BASIC_QUESTION_CSV = new QuestionCSV(
            "W9(2)",
            110,
            BASIC_QUESTION_CONTENT_PL.getContent(),
            "",
            "",
            "",
            BASIC_QUESTION_CONTENT_EN.getContent(),
            "",
            "",
            "",
            BASIC_QUESTION_CONTENT_DE.getContent(),
            "",
            "",
            "",
            'N',
            "AK_D11_45org.wmv",
            "PODSTAWOWY",
            3,
            "A,B"
    );

}
