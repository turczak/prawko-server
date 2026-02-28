package pl.prawko.prawko_server.dto;

import org.springframework.lang.NonNull;
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
        @NonNull String name,
        @NonNull List<AnswerDto> answers,
        @NonNull String media,
        @NonNull QuestionType type,
        int value,
        @NonNull List<String> categories,
        @NonNull List<QuestionTranslation> translations

) {
}
