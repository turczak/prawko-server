package pl.prawko.prawko_server.service;

import org.springframework.web.multipart.MultipartFile;
import pl.prawko.prawko_server.mapper.QuestionMapper;
import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionCSV;

import java.util.List;

/**
 * Service interface for managing {@link Question} entities.
 */
public interface IQuestionService {

    /**
     * Parses the provided CSV file into a list of {@link Question} entities.
     * <p>
     * The CSV file is expected to have a header row and use commas as column separators.
     * Each row is mapped to a {@link QuestionCSV} object, which is converted to a {@link Question} entity using {@link QuestionMapper}.
     * </p>
     *
     * @param file the CSV file containing questions data
     * @return a list of {@link Question} entities
     */
    List<Question> parseFileToQuestions(final MultipartFile file);

    /**
     * Saves provided list of {@link Question} entities to the database.
     *
     * @param questions the list of questions to save
     */
    void saveAll(List<Question> questions);

}
