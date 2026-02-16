package pl.prawko.prawko_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.prawko.prawko_server.model.Exam;

/**
 * Repository for {@link Exam} entities.
 * <p>
 * Provides standard CRUD operations through {@link JpaRepository}.
 */
@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
}
