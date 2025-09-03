package pl.prawko.prawko_server.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.prawko.prawko_server.model.QuestionType;
import pl.prawko.prawko_server.repository.ExamRepository;
import pl.prawko.prawko_server.service.implementation.CategoryService;
import pl.prawko.prawko_server.service.implementation.ExamService;
import pl.prawko.prawko_server.service.implementation.QuestionService;
import pl.prawko.prawko_server.service.implementation.UserService;
import pl.prawko.prawko_server.test_utils.CategoryTestData;
import pl.prawko.prawko_server.test_utils.ExamTestData;
import pl.prawko.prawko_server.test_utils.QuestionTestData;
import pl.prawko.prawko_server.test_utils.UserTestData;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExamServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private QuestionService questionService;

    @Mock
    private ExamRepository repository;

    @InjectMocks
    private ExamService service;


    @Test
    void createExam_returnExam_whenUserAndCategoryFound() {
        final var user = UserTestData.TESTER;
        final var category = CategoryTestData.CATEGORY_B;
        final var categoryName = category.getName();
        final var expected = ExamTestData.EXAM;
        when(userService.getById(user.getId())).thenReturn(user);
        when(categoryService.findByName(categoryName)).thenReturn(category);
        when(questionService.getAllByTypeAndCategory(QuestionType.BASIC, categoryName))
                .thenReturn(QuestionTestData.getBasicQuestions());
        when(questionService.getAllByTypeAndCategory(QuestionType.SPECIAL, categoryName))
                .thenReturn(QuestionTestData.getSpecialQuestions());

        final var result = service.createExam(user.getId(), categoryName);

        assertThat(result).isEqualTo(expected);
        verify(repository).save(result);
        verifyNoMoreInteractions(repository);
    }

}
