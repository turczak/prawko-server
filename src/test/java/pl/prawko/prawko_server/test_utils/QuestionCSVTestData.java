package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.QuestionCSV;

import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_A_ENG;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_A_GER;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_A_POL;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_B_ENG;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_B_GER;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_B_POL;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_C_ENG;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_C_GER;
import static pl.prawko.prawko_server.test_utils.AnswerTranslationTestData.ANSWER_C_POL;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.BASIC_QUESTION_CONTENT_ENG;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.BASIC_QUESTION_CONTENT_GER;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.BASIC_QUESTION_CONTENT_POL;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.SPECIAL_QUESTION_CONTENT_ENG;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.SPECIAL_QUESTION_CONTENT_GER;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.SPECIAL_QUESTION_CONTENT_POL;

public class QuestionCSVTestData {

    public static final QuestionCSV SPECIAL_QUESTION_CSV;

    static {
        SPECIAL_QUESTION_CSV = new QuestionCSV(
                "PD10(3)",
                2143,
                SPECIAL_QUESTION_CONTENT_POL.getContent(),
                ANSWER_A_POL.getContent(),
                ANSWER_B_POL.getContent(),
                ANSWER_C_POL.getContent(),
                SPECIAL_QUESTION_CONTENT_ENG.getContent(),
                ANSWER_A_ENG.getContent(),
                ANSWER_B_ENG.getContent(),
                ANSWER_C_ENG.getContent(),
                SPECIAL_QUESTION_CONTENT_GER.getContent(),
                ANSWER_A_GER.getContent(),
                ANSWER_B_GER.getContent(),
                ANSWER_C_GER.getContent(),
                'B',
                "R_101org.jpg",
                "SPECJALISTYCZNY",
                2,
                "PT"
        );
    }

    public static final QuestionCSV BASIC_QUESTION_CSV;

    static {
        BASIC_QUESTION_CSV = new QuestionCSV(
                "W9(2)",
                110,
                BASIC_QUESTION_CONTENT_POL.getContent(),
                "",
                "",
                "",
                BASIC_QUESTION_CONTENT_ENG.getContent(),
                "",
                "",
                "",
                BASIC_QUESTION_CONTENT_GER.getContent(),
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

}
