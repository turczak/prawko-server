package pl.prawko.prawko_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.prawko.prawko_server.model.Question;

/**
 * Repository for {@link Question} entities.
 * <p>
 * Provides standard CRUD operations through {@link JpaRepository} and custom methods.
 * </p>
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
