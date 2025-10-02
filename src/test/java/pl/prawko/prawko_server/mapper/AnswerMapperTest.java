package pl.prawko.prawko_server.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.prawko.prawko_server.model.Language;
import pl.prawko.prawko_server.service.implementation.LanguageService;
import pl.prawko.prawko_server.test_utils.AnswerTestData;
import pl.prawko.prawko_server.test_utils.LanguageTestData;
import pl.prawko.prawko_server.test_utils.QuestionCSVTestData;
import pl.prawko.prawko_server.test_utils.QuestionTestData;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerMapperTest {

    @Mock
    private LanguageService languageService;

    @InjectMocks
    private AnswerMapper mapper;

    private final List<Language> languages = LanguageTestData.ALL;

    @BeforeEach
    void setUp() {
        mapper = new AnswerMapper(languageService);
    }

    @Test
    void fromQuestionCSVToAnswers_correctlyMapBasicAnswers() {
        final var given = QuestionCSVTestData.BASIC_QUESTION_CSV;
        final var expected = QuestionTestData.BASIC_QUESTION;

        final var result = mapper.fromQuestionCSVToAnswers(given, expected);

        assertThat(result).isEqualTo(expected.getAnswers());
    }

    @Test
    void fromQuestionsCSVToAnswers_correctlyMapSpecialAnswers() {
        final var given = QuestionCSVTestData.SPECIAL_QUESTION_CSV;
        final var expected = QuestionTestData.SPECIAL_QUESTION;
        when(languageService.findAll()).thenReturn(languages);

        final var result = mapper.fromQuestionCSVToAnswers(given, expected);

        assertThat(result).isEqualTo(expected.getAnswers());
    }

    @Test
    void toDto_correctlyMapAnswer() {
        final var given = AnswerTestData.ANSWER_A;
        final var expected = AnswerTestData.ANSWER_A_MAPPED;

        final var result = mapper.toDto(given);

        assertThat(result).isEqualTo(expected);
    }

}
