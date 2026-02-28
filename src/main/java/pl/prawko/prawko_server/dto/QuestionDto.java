package pl.prawko.prawko_server.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionTranslation;
import pl.prawko.prawko_server.model.QuestionType;

import java.util.List;

/**
 * Data Transfer Object representing a {@link Question}
 * <p>
 * This DTO is used to transfer question data between the API layer and the service layer without exposing the full model.
 */
public record QuestionDto(

        long id,
        @NotNull String name,
        @NotEmpty List<AnswerDto> answers,
        @NotNull String media,
        @NotNull QuestionType type,
        int value,
        @NotEmpty List<String> categories,
        @NotEmpty List<QuestionTranslation> translations

) {
}
