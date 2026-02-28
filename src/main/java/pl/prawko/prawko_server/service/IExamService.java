package pl.prawko.prawko_server.service;

import org.springframework.lang.NonNull;
import pl.prawko.prawko_server.dto.ExamDto;
import pl.prawko.prawko_server.model.Exam;

import java.util.Optional;

/**
 * Interface to manage {@link Exam} entities.
 */
public interface IExamService {

    /**
     * Generates a new {@code exam} for specific {@code user} using category name.
     *
     * @param userId       owner of exam
     * @param categoryName category used to construct an exam
     * @return a correctly generated {@code exam}
     */
    @NonNull
    Optional<Exam> createExam(final long userId, @NonNull final String categoryName);

    /**
     * Finds a {@link Exam} entity by its id and map it to {@link ExamDto}.
     *
     * @param examId id of exam to look for
     * @return mapped {@code exam}
     */
    @NonNull
    ExamDto getById(final long examId);

}
