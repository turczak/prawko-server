package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionType;

import java.util.List;

import static pl.prawko.prawko_server.test_utils.AnswerTestData.BASIC_ANSWERS;
import static pl.prawko.prawko_server.test_utils.AnswerTestData.SPECIAL_ANSWERS;
import static pl.prawko.prawko_server.test_utils.CategoryTestData.CATEGORIES_AB;
import static pl.prawko.prawko_server.test_utils.CategoryTestData.CATEGORY_PT;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.getBasicQuestionTranslations;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.getSpecialQuestionTranslations;

public class QuestionTestData {

    public static Question BASIC_QUESTION = new Question()
            .setName("W9(2)")
            .setId(110)
            .setMedia("AK_D11_45org.webm")
            .setType(QuestionType.BASIC)
            .setPoints(3)
            .setCategories(CATEGORIES_AB)
            .setTranslations(getBasicQuestionTranslations())
            .setAnswers(BASIC_ANSWERS);

    static {
        getBasicQuestionTranslations()
                .forEach(translation -> translation.setQuestion(BASIC_QUESTION));
    }

    public static final Question SPECIAL_QUESTION = new Question()
            .setName("PD10(3)")
            .setId(2143)
            .setMedia("R_101org.jpg")
            .setType(QuestionType.SPECIAL)
            .setPoints(2)
            .setCategories(List.of(CATEGORY_PT))
            .setTranslations(getSpecialQuestionTranslations())
            .setAnswers(SPECIAL_ANSWERS);

    static {
        getSpecialQuestionTranslations()
                .forEach(translation -> translation.setQuestion(SPECIAL_QUESTION));
    }

}
