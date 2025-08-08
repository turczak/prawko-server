package pl.prawko.prawko_server.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.prawko.prawko_server.model.Answer;
import pl.prawko.prawko_server.model.AnswerTranslation;
import pl.prawko.prawko_server.model.Language;
import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionCSV;
import pl.prawko.prawko_server.service.implementation.LanguageService;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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
        return Stream.of('Y', 'N')
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
                    answer.setTranslations(translations);
                    return answer;
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
                                            getTranslationContent(language, label, questionCSV)))
                            .toList();
                    answer.setTranslations(translations);
                    return answer;
                })
                .toList();
    }

    private String getTranslationContent(final Language language,
                                         final char label,
                                         final QuestionCSV questionCSV) {
        try {
            final var methodName = "answer_" + label + "_" + language.getCode();
            final var method = questionCSV.getClass()
                    .getMethod(methodName);
            return method.invoke(questionCSV).toString();
        } catch (Exception exception) {
            System.err.println("Error invoking method " + exception.getMessage());
        }
        return null;
    }

}
