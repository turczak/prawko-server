package pl.prawko.prawko_server.mapper;

import org.springframework.stereotype.Component;
import pl.prawko.prawko_server.model.Language;
import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionCSV;
import pl.prawko.prawko_server.model.QuestionTranslation;
import pl.prawko.prawko_server.model.QuestionType;
import pl.prawko.prawko_server.service.implementation.CategoryService;
import pl.prawko.prawko_server.service.implementation.LanguageService;

import java.util.Comparator;
import java.util.List;

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
