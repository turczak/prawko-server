package pl.prawko.prawko_server.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.prawko.prawko_server.model.*;
import pl.prawko.prawko_server.service.implementation.CategoryService;
import pl.prawko.prawko_server.service.implementation.LanguageService;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class CSVMapper {

    private static final List<Character> LABELS = List.of('A', 'B', 'C');

    private final CategoryService categoryService;
    private final LanguageService languageService;

    public Question mapQuestionCSVToQuestion(final QuestionCSV questionCSV) {
        final var question = new Question()
                .withId(questionCSV.id())
                .withName(questionCSV.name())
                .withType(
                        getTypeFromCSV(questionCSV.type()))
                .withMedia(
                        questionCSV.mediaName()
                                .replaceAll("\\.wmv$", ".webm"))
                .withValue(questionCSV.value());
        question.setCategories(
                getCategoriesFromCSV(questionCSV.categories()));
        question.setTranslations(
                mapQuestionTranslations(questionCSV, question));
        question.setAnswers(
                getAnswerTranslationsFromCSV(questionCSV, question));
        return question;
    }

    private QuestionType getTypeFromCSV(final String type) {
        return switch (type) {
            case "PODSTAWOWY" -> QuestionType.BASIC;
            case "SPECJALISTYCZNY" -> QuestionType.SPECIAL;
            default -> throw new IllegalStateException("Unexpected type: " + type);
        };
    }

    private List<Category> getCategoriesFromCSV(final String categories) {
        return Arrays.stream(
                        categories.split(","))
                .map(categoryService::findByName)
                .toList();
    }

    private List<Answer> getAnswerTranslationsFromCSV(final QuestionCSV questionCSV,
                                                      final Question question) {
        switch (questionCSV.type()) {
            case "PODSTAWOWY" -> {
                return mapBasicQuestionAnswers(questionCSV, question);
            }
            case "SPECJALISTYCZNY" -> {
                List<Answer> answers = getSpecialQuestionAnswersFromCSV(questionCSV, question);
                answers.stream()
                        .filter(answer -> questionCSV.correctAnswer() == answer.getLabel())
                        .forEach(answer -> answer.setCorrect(true));
                return answers;
            }
            default -> throw new IllegalStateException("Unexpected value: " + questionCSV.type());
        }
    }

    private List<Answer> getSpecialQuestionAnswersFromCSV(final QuestionCSV questionCSV,
                                                          final Question question) {
        final var languages = languageService.findAll();
        return LABELS.stream()
                .map(label -> {
                    Answer answer = new Answer()
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
        } catch (NoSuchMethodException e) {
            System.err.println("Provided method not found: " + e.getMessage());
        } catch (InvocationTargetException e) {
            System.err.println("Error invoking a method: " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.err.println("Illegal access to method: " + e.getMessage());
        }
        return null;
    }

    private List<Answer> mapBasicQuestionAnswers(QuestionCSV csvLine, Question question) {
        boolean isCorrectAnswerT = csvLine.correctAnswer() == 'T';

        Answer answerYes = new Answer()
                .withQuestion(question)
                .withLabel('T')
                .withCorrect(isCorrectAnswerT);
        List<AnswerTranslation> answerYesTranslations = new ArrayList<>();

        Answer answerNo = new Answer()
                .withQuestion(question)
                .withLabel('N')
                .withCorrect(!isCorrectAnswerT);
        List<AnswerTranslation> answerNoTranslations = new ArrayList<>();

        for (Language language : languageService.findAll()) {
            AnswerTranslation positiveTranslation = new AnswerTranslation()
                    .withAnswer(answerYes)
                    .withLanguage(language);
            AnswerTranslation negativeTranslation = new AnswerTranslation()
                    .withAnswer(answerNo)
                    .withLanguage(language);

            positiveTranslation.setContent(
                    switch (language.getCode().toUpperCase()) {
                        case "PL" -> "Tak";
                        case "EN" -> "Yes";
                        case "DE" -> "Ja";
                        default -> throw new IllegalStateException("Unexpected value: " + language.getCode().toUpperCase());
                    }
            );
            negativeTranslation.setContent(
                    switch (language.getCode().toUpperCase()) {
                        case "PL" -> "Nie";
                        case "EN" -> "No";
                        case "DE" -> "Nein";
                        default -> throw new IllegalStateException("Unexpected value: " + language.getCode().toUpperCase());
                    }
            );
            answerYesTranslations.add(positiveTranslation);
            answerNoTranslations.add(negativeTranslation);
        }

        answerYes.setTranslations(answerYesTranslations);
        answerNo.setTranslations(answerNoTranslations);

        return List.of(answerYes, answerNo);
    }

    private List<QuestionTranslation> mapQuestionTranslations(final QuestionCSV questionCSV,
                                                              final Question question) {
        return languageService.findAll().stream()
                .map(language -> {
                    final var content = switch (language.getCode()) {
                        case "pol" -> questionCSV.content_pol();
                        case "eng" -> questionCSV.content_eng();
                        case "ger" -> questionCSV.content_ger();
                        default -> throw new IllegalStateException("Unexpected language: " + language.getCode());
                    };
                    return new QuestionTranslation()
                            .withQuestion(question)
                            .withLanguage(language)
                            .withContent(content);
                })
                .toList();
    }
}
