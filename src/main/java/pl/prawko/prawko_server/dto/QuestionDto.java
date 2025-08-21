package pl.prawko.prawko_server.dto;

import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionTranslation;
import pl.prawko.prawko_server.model.QuestionType;

import java.util.List;

/**
 * Data Transfer Object representing a {@link Question}
 * <p>
 * This DTO is used to transfer question data between the API layer and the service layer without exposing full model.
 */
public record QuestionDto(

        long id,
        String name,
        List<AnswerDto> answers,
        String media,
        QuestionType type,
        int value,
        List<String> categories,
        List<QuestionTranslation> translations

) {
}
