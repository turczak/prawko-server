package pl.prawko.prawko_server.mapper;

import org.springframework.stereotype.Component;
import pl.prawko.prawko_server.model.Answer;
import pl.prawko.prawko_server.model.AnswerTranslation;
import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionCSV;
import pl.prawko.prawko_server.model.QuestionType;
import pl.prawko.prawko_server.service.implementation.LanguageService;

import java.util.List;

@Component
public class AnswerMapper {

    private final LanguageService languageService;

    public AnswerMapper(final LanguageService languageService) {
        this.languageService = languageService;
    }

    private static final List<Character> SPECIAL_LABELS = List.of('A', 'B', 'C');

    public List<Answer> fromQuestionCSVToAnswers(final QuestionCSV questionCSV,
                                                 final Question question) {
        return switch (QuestionType.ofType(questionCSV.type())) {
            case BASIC -> List.of(
                    new Answer()
                            .setQuestion(question)
                            .setCorrect(true),
                    new Answer()
                            .setQuestion(question)
                            .setCorrect(false)
            );
            case SPECIAL -> mapSpecialQuestionAnswers(questionCSV, question);
        };
    }

    private List<Answer> mapSpecialQuestionAnswers(final QuestionCSV questionCSV,
                                                   final Question question) {
        final var languages = languageService.findAll();
        return SPECIAL_LABELS.stream()
                .map(label -> {
                    final var answer = new Answer()
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
