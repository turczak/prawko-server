package pl.prawko.prawko_server.service.implementation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import pl.prawko.prawko_server.mapper.QuestionMapper;
import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionCSV;
import pl.prawko.prawko_server.repository.QuestionRepository;
import pl.prawko.prawko_server.service.IQuestionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class QuestionService implements IQuestionService {

    private final QuestionRepository repository;
    private final QuestionMapper mapper;

    public QuestionService(final QuestionRepository repository,
                           final QuestionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Question> parseFileToQuestions(final MultipartFile file) {
        if (!"text/csv".equals(file.getContentType())) {
            throw new MultipartException("Invalid file format.");
        }
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            final var csvMapper = new CsvMapper();
            csvMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            final var schema = CsvSchema.emptySchema()
                    .withHeader()
                    .withColumnSeparator(',')
                    .withQuoteChar('"');
            final MappingIterator<QuestionCSV> csvRows = csvMapper
                    .readerFor(QuestionCSV.class)
                    .with(schema)
                    .readValues(reader);
            final var questionCSVs = csvRows.readAll();
            return mapQuestionCSVModelsToQuestions(questionCSVs);
        } catch (IOException exception) {
            throw new RuntimeException("CSV file failed to parse: " + exception.getMessage());
        }
    }

    @Override
    public void saveAll(final List<Question> questions) {
        repository.saveAll(questions);
    }

    private List<Question> mapQuestionCSVModelsToQuestions(final List<QuestionCSV> questionCSVs) {
        return questionCSVs.stream()
                .map(mapper::mapQuestionCSVToQuestion)
                .toList();
    }

}
