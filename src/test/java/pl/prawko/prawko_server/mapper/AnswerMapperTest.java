package pl.prawko.prawko_server.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.prawko.prawko_server.model.AnswerTranslation;
import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.service.implementation.LanguageService;
import pl.prawko.prawko_server.util.TestDataUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static pl.prawko.prawko_server.util.TestDataUtil.LANGUAGES;

@ExtendWith(MockitoExtension.class)
class AnswerMapperTest {

    @Mock
    private LanguageService languageService;

    @InjectMocks
    private AnswerMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new AnswerMapper(languageService);
        when(languageService.findAll())
                .thenReturn(LANGUAGES);
    }

    @Test
    void fromQuestionCSVToAnswers_correctlyMapBasicAnswers() {
        final var given = TestDataUtil.BASIC_QUESTION_CSV;
        final var expected = TestDataUtil.BASIC_QUESTION;
        final var answers = mapper.fromQuestionCSVToAnswers(given, expected);
        assertThat(answers)
                .hasSize(2)
                .allSatisfy(answer -> {
                    assertThat(answer.getLabel())
                            .isIn('Y', 'N');
                    assertThat(answer.getTranslations())
                            .hasSize(3);
                    assertThat(answer.getQuestion())
                            .isEqualTo(expected);
                    assertThat(answer.getTranslations())
                            .hasSize(3)
                            .allSatisfy(translation -> {
                                assertThat(translation.getAnswer())
                                        .isEqualTo(answer);
                                assertThat(translation.getLanguage())
                                        .isIn(LANGUAGES);
                                assertThat(translation.getContent())
                                        .isIn(
                                                getAllTranslationsContents(expected));
                            });
                });
    }

    @Test
    void fromQuestionsCSVToAnswers_correctlyMapSpecialAnswers() {
        final var given = TestDataUtil.SPECIAL_QUESTION_CSV;
        final var expected = TestDataUtil.SPECIAL_QUESTION;
        final var answers = mapper.fromQuestionCSVToAnswers(given, expected);
        assertThat(answers)
                .hasSize(3)
                .allSatisfy(answer -> {
                    assertThat(answer.getLabel())
                            .isIn('A', 'B', 'C');
                    assertThat(answer.getTranslations())
                            .hasSize(3);
                    assertThat(answer.getQuestion())
                            .isEqualTo(expected);
                    assertThat(answer.getTranslations())
                            .hasSize(3)
                            .allSatisfy(translation -> {
                                assertThat(translation.getAnswer())
                                        .isEqualTo(answer);
                                assertThat(translation.getLanguage())
                                        .isIn(LANGUAGES);
                                assertThat(translation.getContent())
                                        .isIn(
                                                getAllTranslationsContents(expected));
                            });
                });
    }

    private List<String> getAllTranslationsContents(final Question expected) {
        return expected.getAnswers().stream()
                .flatMap(ans -> ans.getTranslations().stream())
                .map(AnswerTranslation::getContent)
                .toList();
    }

}
