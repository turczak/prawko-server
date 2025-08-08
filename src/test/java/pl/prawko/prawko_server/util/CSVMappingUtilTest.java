package pl.prawko.prawko_server.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import pl.prawko.prawko_server.mapper.AnswerMapper;
import pl.prawko.prawko_server.mapper.QuestionMapper;
import pl.prawko.prawko_server.service.implementation.CategoryService;
import pl.prawko.prawko_server.service.implementation.LanguageService;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static pl.prawko.prawko_server.util.TestDataUtil.*;

@ExtendWith(MockitoExtension.class)
public class CSVMappingUtilTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private LanguageService languageService;

    @Mock
    private AnswerMapper answerMapper;

    private CSVMappingUtil csvMappingUtil;

    @BeforeEach
    void setUp() {
        final var questionMapper = new QuestionMapper(categoryService, languageService, answerMapper);
        csvMappingUtil = new CSVMappingUtil(questionMapper);
    }

    @Test
    void mapCSVFile_correctly() throws IOException {
        final ClassPathResource resource = new ClassPathResource("test_question.csv");
        assertThat(resource)
                .satisfies(res -> {
                    assertThat(res.exists()).isTrue();
                    assertThat(res.isReadable()).isTrue();
                });
        final var inputStream = resource.getInputStream();
        final var file = new MockMultipartFile(
                "file",
                "test_question.csv",
                "text/csv",
                inputStream);
        final var result = csvMappingUtil.mapFileToQuestionCSVModels(file);
        assertThat(result)
                .hasSize(2)
                .containsExactly(SPECIAL_QUESTION_CSV, BASIC_QUESTION_CSV);
    }

    @Test
    void mapQuestionCSVModelsToQuestions_mapBothTypeOfQuestions() {
        when(categoryService.findAllFromString("A,B"))
                .thenReturn(CATEGORIES);
        when(categoryService.findAllFromString("PT"))
                .thenReturn(
                        List.of(CATEGORY_PT));
        when(languageService.findAll())
                .thenReturn(LANGUAGES);
        final var given = List.of(BASIC_QUESTION_CSV, SPECIAL_QUESTION_CSV);
        final var expected = List.of(BASIC_QUESTION, SPECIAL_QUESTION);
        final var result = csvMappingUtil.mapQuestionCSVModelsToQuestions(given);
        assertThat(result)
                .isEqualTo(expected);
    }

}
