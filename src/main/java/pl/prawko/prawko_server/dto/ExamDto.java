package pl.prawko.prawko_server.dto;

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
        LocalDateTime created,
        LocalDateTime updated,
        List<QuestionDto> questions,
        List<AnswerDto> userAnswers,
        int score,
        boolean active

) {
}
