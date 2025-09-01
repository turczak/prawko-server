package pl.prawko.prawko_server.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.prawko.prawko_server.test_utils.CategoryTestData;
import pl.prawko.prawko_server.test_utils.QuestionTestData;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class QuestionRepositoryTest {

    private static final String[] IGNORED_FIELDS = {
            "answers.id",
            "answers.translations.id",
            "answers.translations.language.answerTranslations",
            "answers.translations.language.questionTranslations",
            "answers.translations.language.exams",
            "translations.id",
            "translations.language",
            "categories.questions",
            "categories.exams"};

    @Autowired
    private QuestionRepository repository;

    @Test
    void saveAll_correctly() {
        final var question1 = QuestionTestData.BASIC_QUESTION;
        final var question2 = QuestionTestData.SPECIAL_QUESTION;
        final var expected = List.of(question1, question2);
        repository.saveAll(expected);
        final var result = repository.findAll();

        assertThat(result)
                .usingRecursiveComparison()
                .ignoringFields(IGNORED_FIELDS)
                .isEqualTo(expected);
    }

    @Test
    void findByTypeAndCategoriesContaining() {
        final var question = QuestionTestData.BASIC_QUESTION;
        final var category = CategoryTestData.CATEGORY_B;
        repository.save(question);
        final var expected = List.of(question);
        final var result = repository.findByTypeAndCategories_NameContains(question.getType(), category.getName());

        assertThat(result)
                .usingRecursiveComparison()
                .ignoringFields(IGNORED_FIELDS)
                .isEqualTo(expected);
    }

}
