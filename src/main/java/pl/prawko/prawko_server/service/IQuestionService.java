package pl.prawko.prawko_server.service;

import org.springframework.web.multipart.MultipartFile;
import pl.prawko.prawko_server.model.Question;

import java.util.List;

public interface IQuestionService {

    List<Question> parseFileToQuestions(final MultipartFile file);

    void saveAll(List<Question> questions);

}
