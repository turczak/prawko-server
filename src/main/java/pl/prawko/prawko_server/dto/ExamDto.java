package pl.prawko.prawko_server.dto;

import org.springframework.lang.NonNull;
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
        @NonNull LocalDateTime created,
        @NonNull LocalDateTime updated,
        @NonNull List<QuestionDto> questions,
        @NonNull List<AnswerDto> userAnswers,
        int score,
        boolean active

) {
}
