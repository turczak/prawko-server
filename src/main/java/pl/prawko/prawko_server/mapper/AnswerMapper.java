package pl.prawko.prawko_server.mapper;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import pl.prawko.prawko_server.dto.AnswerDto;
import pl.prawko.prawko_server.dto.AnswerTranslationDto;
import pl.prawko.prawko_server.model.Answer;
import pl.prawko.prawko_server.model.AnswerTranslation;
import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionCSV;
import pl.prawko.prawko_server.model.QuestionType;
import pl.prawko.prawko_server.service.implementation.LanguageService;

import java.util.List;

/**
 * This class is responsible for mapping {@link QuestionCSV} model into {@link Answer} entity.
 * <p>
 * The mapper is registered as a Spring {@link Component}, so it can be injected into services or other components that require answer mapping
 * functionality.
 */
@Component
public class AnswerMapper {

    @NonNull
    private final LanguageService languageService;

    public AnswerMapper(@NonNull final LanguageService languageService) {
        this.languageService = languageService;
    }

    private static final List<Character> SPECIAL_LABELS = List.of('A', 'B', 'C');

    /**
     * Maps {@link Answer} entity into {@link AnswerDto}.
     *
     * @param answer model to map
     * @return mapped model
     */
    @NonNull
    public AnswerDto toDto(@NonNull final Answer answer) {
        return new AnswerDto(
                answer.getId(),
                answer.getQuestion().getId(),
                answer.isCorrect(),
                translationsToDtos(answer.getTranslations()));
    }

    /**
     * This method recognizes question types and creates basic or special answers.
     *
     * @param questionCSV CSV model to map answers from
     * @param question    {@link Question} entity that answers would be linked to
     * @return list of basic or special {@link Answer} entities
     */
    @NonNull
    public List<Answer> fromQuestionCSVToAnswers(@NonNull final QuestionCSV questionCSV,
                                                 @NonNull final Question question) {
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

    /**
     * This method is responsible for creating special {@link Answer} with their translations from the CSV model.
     *
     * @param questionCSV CSV model to map answers with translations from
     * @param question    {@link Question} entity that answers would be linked to
     * @return list of special {@link Answer} entities
     */
    @NonNull
    private List<Answer> mapSpecialQuestionAnswers(@NonNull final QuestionCSV questionCSV,
                                                   @NonNull final Question question) {
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

    @NonNull
    private List<AnswerTranslationDto> translationsToDtos(@NonNull final List<AnswerTranslation> translations) {
        return translations.stream()
                .map(translation ->
                        new AnswerTranslationDto(translation.getContent(), translation.getLanguage().getCode()))
                .toList();
    }

}
