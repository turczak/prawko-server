package pl.prawko.prawko_server.mapper;

import org.springframework.stereotype.Component;
import pl.prawko.prawko_server.model.Answer;
import pl.prawko.prawko_server.model.AnswerTranslation;
import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionCSV;
import pl.prawko.prawko_server.service.implementation.LanguageService;

import java.util.List;
import java.util.Map;

@Component
public class AnswerMapper {

    private final LanguageService languageService;

    public AnswerMapper(final LanguageService languageService) {
        this.languageService = languageService;
    }

    private static final List<Character> SPECIAL_LABELS = List.of('A', 'B', 'C');
    private static final Map<Character, Map<String, String>> BASIC_TRANSLATIONS = Map.of(
            'Y', Map.of(
                    "pol", "Tak",
                    "eng", "Yes",
                    "ger", "Ja"
            ),
            'N', Map.of(
                    "pol", "Nie",
                    "eng", "No",
                    "ger", "Nein"
            )
    );

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
                            .setQuestion(question)
                            .setLabel(label)
                            .setCorrect(correctAnswer == label);
                    final var translations = languageService.findAll().stream()
                            .map(language -> new AnswerTranslation()
                                    .setAnswer(answer)
                                    .setLanguage(language)
                                    .setContent(
                                            BASIC_TRANSLATIONS
                                                    .get(answer.getLabel())
                                                    .get(language.getCode())))
                            .toList();
                    return answer.setTranslations(translations);
                })
                .toList();
    }

    private List<Answer> mapSpecialQuestionAnswers(final QuestionCSV questionCSV,
                                                   final Question question) {
        final var languages = languageService.findAll();
        return SPECIAL_LABELS.stream()
                .map(label -> {
                    final var answer = new Answer()
                            .setLabel(label)
                            .setQuestion(question)
                            .setCorrect(label == questionCSV.correctAnswer());
                    final var translations = languages.stream()
                            .map(language -> new AnswerTranslation()
                                    .setLanguage(language)
                                    .setAnswer(answer)
                                    .setContent(
                                            questionCSV.getAnswersTranslations()
                                                    .get(language.getCode())
                                                    .get(label)))
                            .toList();
                    return answer.setTranslations(translations);
                })
                .toList();
    }

}
