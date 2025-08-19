package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionType;

import java.util.List;

import static pl.prawko.prawko_server.test_utils.AnswerTestData.getBasicAnswers;
import static pl.prawko.prawko_server.test_utils.AnswerTestData.getSpecialAnswers;
import static pl.prawko.prawko_server.test_utils.CategoryTestData.CATEGORIES_AB;
import static pl.prawko.prawko_server.test_utils.CategoryTestData.CATEGORY_PT;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.getBasicQuestionTranslations;
import static pl.prawko.prawko_server.test_utils.QuestionTranslationTestData.getSpecialQuestionTranslations;

public class QuestionTestData {

    public static final Question BASIC_QUESTION = new Question()
            .setName("W9(2)")
            .setId(110)
            .setMedia("AK_D11_45org.webm")
            .setType(QuestionType.BASIC)
            .setPoints(3)
            .setCategories(CATEGORIES_AB)
            .setTranslations(getBasicQuestionTranslations())
            .setAnswers(getBasicAnswers());

    public static final Question SPECIAL_QUESTION = new Question()
            .setName("PD10(3)")
            .setId(2143)
            .setMedia("R_101org.jpg")
            .setType(QuestionType.SPECIAL)
            .setPoints(2)
            .setCategories(List.of(CATEGORY_PT))
            .setTranslations(getSpecialQuestionTranslations())
            .setAnswers(getSpecialAnswers());

    static {
        getBasicQuestionTranslations().forEach(translation -> translation.setQuestion(BASIC_QUESTION));
        getSpecialQuestionTranslations().forEach(translation -> translation.setQuestion(SPECIAL_QUESTION));
        getBasicAnswers().forEach(answer -> answer.setQuestion(BASIC_QUESTION));
        getSpecialAnswers().forEach(answer -> {
            answer.setQuestion(SPECIAL_QUESTION);
            answer.getTranslations()
                    .forEach(translation -> translation.setAnswer(answer));
        });
    }

}
