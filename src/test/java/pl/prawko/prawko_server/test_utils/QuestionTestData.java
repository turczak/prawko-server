package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionType;

import java.util.List;

import static pl.prawko.prawko_server.test_utils.AnswerTestData.BASIC_ANSWERS;
import static pl.prawko.prawko_server.test_utils.AnswerTestData.SPECIAL_ANSWERS;
import static pl.prawko.prawko_server.test_utils.CategoryTestData.CATEGORIES_AB;
import static pl.prawko.prawko_server.test_utils.CategoryTestData.CATEGORY_PT;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.BASIC_QUESTION_TRANSLATIONS;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.SPECIAL_QUESTION_TRANSLATIONS;

public class QuestionTestData {

    public static Question BASIC_QUESTION;

    static {
        BASIC_QUESTION = new Question()
                .setName("W9(2)")
                .setId(110)
                .setMedia("AK_D11_45org.webm")
                .setType(QuestionType.BASIC)
                .setPoints(3)
                .setCategories(
                        CATEGORIES_AB);
        BASIC_QUESTION_TRANSLATIONS()
                .forEach(translation -> translation.setQuestion(BASIC_QUESTION));
        BASIC_QUESTION.setTranslations(BASIC_QUESTION_TRANSLATIONS());
        BASIC_QUESTION.setAnswers(BASIC_ANSWERS);
    }

    public static final Question SPECIAL_QUESTION;

    static {
        SPECIAL_QUESTION = new Question()
                .setName("PD10(3)")
                .setId(2143)
                .setMedia("R_101org.jpg")
                .setType(QuestionType.SPECIAL)
                .setPoints(2)
                .setCategories(List.of(CATEGORY_PT));
        SPECIAL_QUESTION_TRANSLATIONS()
                .forEach(translation -> translation.setQuestion(SPECIAL_QUESTION));
        SPECIAL_QUESTION.setTranslations(SPECIAL_QUESTION_TRANSLATIONS());
        SPECIAL_QUESTION.setAnswers(SPECIAL_ANSWERS);
    }

}
