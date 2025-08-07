package pl.prawko.prawko_server.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.prawko.prawko_server.service.implementation.CategoryService;
import pl.prawko.prawko_server.service.implementation.LanguageService;
import pl.prawko.prawko_server.util.TestDataUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CSVMapperTest {

    @Mock
    private LanguageService languageService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CSVMapper CSVMapper;

    @Test
    void mapQuestionCSVToQuestion_success() {
        when(categoryService.findByName("PT"))
                .thenReturn(TestDataUtil.CATEGORY);
        when(languageService.findAll())
                .thenReturn(List.of(
                        TestDataUtil.POL,
                        TestDataUtil.ENG,
                        TestDataUtil.GER
                ));
        final var result = CSVMapper.mapQuestionCSVToQuestion(
                TestDataUtil.QUESTION_CSV);
        final var expected = TestDataUtil.QUESTION;
        assertThat(result)
                .satisfies(question -> {
                    assertThat(question.getName())
                            .isEqualTo(expected.getName());
                    assertThat(question.getId())
                            .isEqualTo(expected.getId());
                    assertThat(question.getType())
                            .isEqualTo(expected.getType());
                    assertThat(question.getMedia())
                            .isEqualTo(expected.getMedia());
                    assertThat(question.getValue())
                            .isEqualTo(expected.getValue());
                    assertThat(question.getCategories())
                            .isEqualTo(expected.getCategories());
                    assertThat(question.getAnswers())
                            .hasSize(3)
                            .allSatisfy(answer -> {
                                assertThat(answer.getLabel())
                                        .isIn('A', 'B', 'C');
                                assertThat(answer.getTranslations())
                                        .hasSize(3);
                                assertThat(answer.getQuestion())
                                        .isEqualTo(question);
                                assertThat(answer.getTranslations())
                                        .hasSize(3)
                                        .allSatisfy(translation -> {
                                            assertThat(translation.getAnswer())
                                                    .isEqualTo(answer);
                                            assertThat(translation.getLanguage())
                                                    .isIn(
                                                            TestDataUtil.POL,
                                                            TestDataUtil.ENG,
                                                            TestDataUtil.GER
                                                    );
                                            assertThat(translation.getContent())
                                                    .isNotBlank();
                                        });
                            });
                    assertThat(question.getTranslations())
                            .hasSize(3)
                            .allSatisfy(translation -> {
                                assertThat(translation.getQuestion())
                                        .isEqualTo(question);
                                assertThat(translation.getLanguage())
                                        .isIn(
                                                TestDataUtil.POL,
                                                TestDataUtil.ENG,
                                                TestDataUtil.GER
                                        );
                                assertThat(translation.getContent())
                                        .isNotBlank();
                            });
                });
    }

}
