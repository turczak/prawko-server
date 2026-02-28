package pl.prawko.prawko_server.dto;

import org.springframework.lang.NonNull;
import pl.prawko.prawko_server.model.Answer;
import pl.prawko.prawko_server.model.Question;

import java.util.List;

/**
 * Data Transfer Object representing an {@link Answer} for a {@link Question}.
 * <p>
 * This DTO is used to transfer answer data between the API layer and the service layer without exposing the full model.
 */
public record AnswerDto(

        long id,
        long questionId,
        boolean correct,
        @NonNull List<AnswerTranslationDto> translations

) {
}
