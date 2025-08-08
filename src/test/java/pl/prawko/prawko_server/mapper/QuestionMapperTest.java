package pl.prawko.prawko_server.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.prawko.prawko_server.model.Language;
import pl.prawko.prawko_server.service.implementation.CategoryService;
import pl.prawko.prawko_server.service.implementation.LanguageService;
import pl.prawko.prawko_server.test_utils.CategoryTestData;
import pl.prawko.prawko_server.test_utils.LanguageTestData;
import pl.prawko.prawko_server.util.TestDataUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionMapperTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private LanguageService languageService;

    @InjectMocks
    private QuestionMapper questionMapper;

    private final List<Language> languages = LanguageTestData.ALL;

    @BeforeEach
    void setUp() {
        final var answerMapper = new AnswerMapper(languageService);
        questionMapper = new QuestionMapper(categoryService, languageService, answerMapper);
        when(languageService.findAll())
                .thenReturn(languages);
    }

    @Test
    void mapQuestionCSVToQuestion_returnBasicQuestion() {
        final var categories = CategoryTestData.CATEGORIES_AB;
        when(categoryService.findAllFromString("A,B"))
                .thenReturn(categories);
        final var given = TestDataUtil.BASIC_QUESTION_CSV;
        final var expected = TestDataUtil.BASIC_QUESTION;
        final var result = questionMapper.mapQuestionCSVToQuestion(given);
        assertThat(result)
                .isEqualTo(expected);
    }

    @Test
    void mapQuestionCSVToQuestion_returnSpecialQuestion() {
        when(categoryService.findAllFromString("PT"))
                .thenReturn(
                        List.of(CategoryTestData.CATEGORY_PT));
        final var given = TestDataUtil.SPECIAL_QUESTION_CSV;
        final var expected = TestDataUtil.SPECIAL_QUESTION;
        final var result = questionMapper.mapQuestionCSVToQuestion(given);
        assertThat(result)
                .isEqualTo(expected);
    }

}
