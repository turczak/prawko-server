package pl.prawko.prawko_server.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.prawko.prawko_server.model.Language;
import pl.prawko.prawko_server.model.QuestionType;
import pl.prawko.prawko_server.service.implementation.LanguageService;
import pl.prawko.prawko_server.test_data.AnswerVariant;
import pl.prawko.prawko_server.test_data.LanguageTestData;
import pl.prawko.prawko_server.test_data.TestDataFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerMapperTest {

    @Mock
    private LanguageService languageService;

    @InjectMocks
    private AnswerMapper mapper;

    private final TestDataFactory testDataFactory = new TestDataFactory();
    private final List<Language> languages = LanguageTestData.ALL;

    @BeforeEach
    void setUp() {
        mapper = new AnswerMapper(languageService);
    }

    @Test
    void fromQuestionCSVToAnswers_correctlyMapBasicAnswers() {
        final var given = testDataFactory.createBasicQuestionCSV();
        final var expected = testDataFactory.createQuestion(QuestionType.BASIC);

        final var result = mapper.fromQuestionCSVToAnswers(given, expected);

        assertThat(result).isEqualTo(expected.getAnswers());
    }

    @Test
    void fromQuestionsCSVToAnswers_correctlyMapSpecialAnswers() {
        final var given = testDataFactory.createSpecialQuestionCSV();
        final var expected = testDataFactory.createQuestion(QuestionType.SPECIAL);
        when(languageService.findAll()).thenReturn(languages);

        final var result = mapper.fromQuestionCSVToAnswers(given, expected);

        assertThat(result).isEqualTo(expected.getAnswers());
    }

    @Test
    void toDto_correctlyMapAnswer() {
        final var given = testDataFactory.createAnswer(AnswerVariant.A);
        final var expected = testDataFactory.createAnswerDtoA();

        final var result = mapper.toDto(given);

        assertThat(result).isEqualTo(expected);
    }

}
