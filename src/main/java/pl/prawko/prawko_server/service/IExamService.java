package pl.prawko.prawko_server.service;

import pl.prawko.prawko_server.model.Exam;

/**
 * Interface to manage {@link Exam} entities.
 */
public interface IExamService {

    /**
     * Generates a new {@code exam} for specific {@code user} using category name.
     *
     * @param userId       owner of exam
     * @param categoryName category used to construct
     * @return a correctly generated {@code exam} that you can start solving
     */
    Exam createExam(final long userId, final String categoryName);

}
