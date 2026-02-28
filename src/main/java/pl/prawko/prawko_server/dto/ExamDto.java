package pl.prawko.prawko_server.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import pl.prawko.prawko_server.model.Exam;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object representing an {@link Exam}.
 * <p>
 * This DTO is used to transfer exam data between the API layer and the service layer without exposing the full model.
 */
public record ExamDto(

        long id,
        long userId,
        @NotNull LocalDateTime created,
        @NotNull LocalDateTime updated,
        @NotEmpty List<QuestionDto> questions,
        @NotEmpty List<AnswerDto> userAnswers,
        int score,
        boolean active

) {
}
