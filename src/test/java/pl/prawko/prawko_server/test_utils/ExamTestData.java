package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.Exam;

import java.util.Collections;
import java.util.stream.Stream;

public class ExamTestData {

    public static final Exam EXAM = new Exam()
            .setUser(UserTestData.TESTER)
            .setCategory(CategoryTestData.CATEGORY_B)
            .setQuestions(Stream.concat(
                            QuestionTestData.getBasicQuestions().stream(),
                            QuestionTestData.getSpecialQuestions().stream())
                    .toList())
            .setScore(0)
            .setActive(true)
            .setUserAnswers(Collections.emptyList());

    static {
        UserTestData.TESTER.getExams().add(EXAM);
    }

}
