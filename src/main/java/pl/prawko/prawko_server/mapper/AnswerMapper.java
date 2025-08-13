package pl.prawko.prawko_server.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.prawko.prawko_server.model.Answer;
import pl.prawko.prawko_server.model.AnswerTranslation;
import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionCSV;
import pl.prawko.prawko_server.service.implementation.LanguageService;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class AnswerMapper {

    private static final List<Character> LABELS = List.of('A', 'B', 'C');
    private static final Map<Character, Map<String, String>> BASIC_TRANSLATIONS = Map.of(
            'Y', Map.of(
                    "pol", "Tak.",
                    "eng", "Yes.",
                    "ger", "Ja."
            ),
            'N', Map.of(
                    "pol", "Nie.",
                    "eng", "No.",
                    "ger", "Nein."
            )
    );

    private final LanguageService languageService;

    public List<Answer> fromQuestionCSVToAnswers(final QuestionCSV questionCSV,
                                                 final Question question) {
        return switch (questionCSV.type()) {
            case "PODSTAWOWY" -> mapBasicQuestionAnswers(questionCSV.correctAnswer(), question);
            case "SPECJALISTYCZNY" -> mapSpecialQuestionAnswers(questionCSV, question);
            default -> throw new IllegalStateException("Unexpected value: " + questionCSV.type());
        };
    }

    private List<Answer> mapBasicQuestionAnswers(final char correctAnswer,
                                                 final Question question) {
        return BASIC_TRANSLATIONS.keySet().stream()
                .map(label -> {
                    final var answer = new Answer()
                            .withQuestion(question)
                            .withLabel(label)
                            .withCorrect(correctAnswer == label);
                    final var translations = languageService.findAll().stream()
                            .map(language -> new AnswerTranslation()
                                    .withAnswer(answer)
                                    .withLanguage(language)
                                    .withContent(
                                            BASIC_TRANSLATIONS
                                                    .get(answer.getLabel())
                                                    .get(language.getCode())))
                            .toList();
                    return answer.withTranslations(translations);
                })
                .toList();
    }

    private List<Answer> mapSpecialQuestionAnswers(final QuestionCSV questionCSV,
                                                   final Question question) {
        final var languages = languageService.findAll();
        return LABELS.stream()
                .map(label -> {
                    final var answer = new Answer()
                            .withLabel(label)
                            .withQuestion(question);
                    final var translations = languages.stream()
                            .map(language -> new AnswerTranslation()
                                    .withLanguage(language)
                                    .withAnswer(answer)
                                    .withContent(
                                            questionCSV.getAnswersTranslations()
                                                    .get(language.getCode())
                                                    .get(label)))
                            .toList();
                    answer.setTranslations(translations);
                    return answer;
                })
                .toList();
    }

}
