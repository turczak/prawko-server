package pl.prawko.prawko_server.mapper;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class QuestionMapper {

    private final CategoryService categoryService;
    private final LanguageService languageService;
    private final AnswerMapper answerMapper;

    public Question mapQuestionCSVToQuestion(final QuestionCSV questionCSV) {
        final var question = new Question()
                .withId(questionCSV.id())
                .withName(questionCSV.name())
                .withType(
                        questionCSV.type().equals("PODSTAWOWY")
                                ? QuestionType.BASIC
                                : QuestionType.SPECIAL)
                .withMedia(
                        questionCSV.mediaName()
                                .replaceAll("\\.wmv$", ".webm"))
                .withValue(questionCSV.value());
        question.setCategories(
                categoryService.findAllFromString(questionCSV.categories()));
        question.setTranslations(
                mapQuestionTranslations(questionCSV, question));
        question.setAnswers(
                answerMapper.fromQuestionCSVToAnswers(questionCSV, question));
        return question;
    }

    private List<QuestionTranslation> mapQuestionTranslations(final QuestionCSV questionCSV,
                                                              final Question question) {
        return languageService.findAll().stream()
                .sorted(Comparator.comparing(Language::getId))
                .map(language -> new QuestionTranslation()
                        .withQuestion(question)
                        .withLanguage(language)
                        .withContent(
                                switch (language.getCode()) {
                                    case "pol" -> questionCSV.content_pol();
                                    case "eng" -> questionCSV.content_eng();
                                    case "ger" -> questionCSV.content_ger();
                                    default -> throw new IllegalStateException("Unexpected language: " + language.getCode());
                                }
                        ))
                .toList();
    }

}
