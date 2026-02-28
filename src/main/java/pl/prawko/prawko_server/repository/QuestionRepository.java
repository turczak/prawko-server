package pl.prawko.prawko_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionType;

import java.util.List;

/**
 * Repository for {@link Question} entities.
 * <p>
 * Provides standard CRUD operations through {@link JpaRepository} and custom methods.
 * </p>
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Nullable
    List<Question> findByTypeAndCategories_NameContains(@NonNull final QuestionType type, @NonNull final String category);

}
