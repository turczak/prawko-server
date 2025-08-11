package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.QuestionCSV;

import static pl.prawko.prawko_server.test_utils.AnswerTestData.SPECIAL_ANSWER_TRANSLATIONS;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.ENG;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.GER;
import static pl.prawko.prawko_server.test_utils.LanguageTestData.POL;
import static pl.prawko.prawko_server.test_utils.QuestionTestData.BASIC_QUESTION_CONTENT;
import static pl.prawko.prawko_server.test_utils.QuestionTestData.SPECIAL_QUESTION_CONTENT;

public class QuestionCSVTestData {

    public static final QuestionCSV SPECIAL_QUESTION_CSV = new QuestionCSV(
            "PD10(3)",
            2143,
            SPECIAL_QUESTION_CONTENT.get(POL),
            SPECIAL_ANSWER_TRANSLATIONS.get(POL).getFirst(),
            SPECIAL_ANSWER_TRANSLATIONS.get(POL).get(1),
            SPECIAL_ANSWER_TRANSLATIONS.get(POL).get(2),
            SPECIAL_QUESTION_CONTENT.get(ENG),
            SPECIAL_ANSWER_TRANSLATIONS.get(ENG).getFirst(),
            SPECIAL_ANSWER_TRANSLATIONS.get(ENG).get(1),
            SPECIAL_ANSWER_TRANSLATIONS.get(ENG).get(2),
            SPECIAL_QUESTION_CONTENT.get(GER),
            SPECIAL_ANSWER_TRANSLATIONS.get(GER).getFirst(),
            SPECIAL_ANSWER_TRANSLATIONS.get(GER).get(1),
            SPECIAL_ANSWER_TRANSLATIONS.get(GER).get(2),
            'B',
            "R_101org.jpg",
            "SPECJALISTYCZNY",
            2,
            "PT"
    );


    public static final QuestionCSV BASIC_QUESTION_CSV = new QuestionCSV(
            "W9(2)",
            110,
            BASIC_QUESTION_CONTENT.get(POL),
            "",
            "",
            "",
            BASIC_QUESTION_CONTENT.get(ENG),
            "",
            "",
            "",
            BASIC_QUESTION_CONTENT.get(GER),
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
