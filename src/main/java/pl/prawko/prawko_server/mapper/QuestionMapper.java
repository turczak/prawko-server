package pl.prawko.prawko_server.mapper;

import org.springframework.stereotype.Component;
import pl.prawko.prawko_server.model.Answer;
import pl.prawko.prawko_server.model.Language;
import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionCSV;
import pl.prawko.prawko_server.model.QuestionTranslation;
import pl.prawko.prawko_server.model.QuestionType;
import pl.prawko.prawko_server.service.implementation.CategoryService;
import pl.prawko.prawko_server.service.implementation.LanguageService;

import java.util.Comparator;
import java.util.List;

/**
 * This class is responsible for mapping {@link QuestionCSV} models into {@link Question} entities.
 * It's using {@link AnswerMapper} to delegate mapping of {@link Answer} entities linked to {@link Question}.
 * Categories are mapped using {@link CategoryService#findAllFromString(String)}.
 * <p>
 * The mapper is registered as a Spring {@link Component}, so it can be injected into services or other components that require question mapping
 * functionality.
 */
@Component
public class QuestionMapper {

    private final CategoryService categoryService;
    private final LanguageService languageService;
    private final AnswerMapper answerMapper;

    public QuestionMapper(final CategoryService categoryService,
                          final LanguageService languageService,
                          final AnswerMapper answerMapper) {
        this.categoryService = categoryService;
        this.languageService = languageService;
        this.answerMapper = answerMapper;
    }

    /**
     * This method is using fluent setters to build a {@code Question} entity.
     * It's using helper methods to get content translations of question.
     *
     * @param questionCSV CSV model to map question from
     * @return mapped {@code Question} entity
     */
    public Question mapQuestionCSVToQuestion(final QuestionCSV questionCSV) {
        final var question = new Question()
                .setId(questionCSV.id())
                .setName(questionCSV.name())
                .setType(QuestionType.ofType(questionCSV.type()))
                .setMedia(questionCSV.mediaName().replaceAll("\\.wmv$", ".webm"))
                .setPoints(questionCSV.value())
                .setCategories(categoryService.findAllFromString(questionCSV.categories()));
        return question
                .setTranslations(mapQuestionTranslations(questionCSV, question))
                .setAnswers(answerMapper.fromQuestionCSVToAnswers(questionCSV, question));
    }

    /**
     * This method create all question content translations from CSV file.
     *
     * @param questionCSV CSV model to map contents from
     * @param question    {@link Question} entity that translations would be linked to
     * @return list of translated
     */
    private List<QuestionTranslation> mapQuestionTranslations(final QuestionCSV questionCSV,
                                                              final Question question) {
        return languageService.findAll().stream()
                .sorted(Comparator.comparing(Language::getId))
                .map(language -> new QuestionTranslation()
                        .setQuestion(question)
                        .setLanguage(language)
                        .setContent(getContent(questionCSV, language)))
                .toList();
    }

    /**
     * Helper method to get translation of question content by {@link Language}.
     *
     * @param questionCSV CSV model to get translation from
     * @param language    language of translation that we are looking for
     * @return content translation in given language
     */
    private String getContent(final QuestionCSV questionCSV,
                              final Language language) {
        return switch (language.getCode()) {
            case Language.PL -> questionCSV.contentPL();
            case Language.EN -> questionCSV.contentEN();
            case Language.DE -> questionCSV.contentDE();
            default -> throw new IllegalStateException("Unexpected language: " + language.getCode());
        };
    }

}
