package pl.prawko.prawko_server.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@AllArgsConstructor
public class CSVMappingUtil {

    public List<QuestionCSVRepresentation> mapFileToListOfQuestionCSVRepresentation(final MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            final var csvMapper = new CsvMapper();
            csvMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            final var schema = CsvSchema.emptySchema()
                    .withHeader()
                    .withColumnSeparator(',')
                    .withQuoteChar('"');
            final MappingIterator<QuestionCSVRepresentation> csvRows = csvMapper
                    .readerFor(QuestionCSVRepresentation.class)
                    .with(schema)
                    .readValues(reader);
            return csvRows.readAll();
        } catch (IOException exception) {
            throw new RuntimeException("CSV file failed to parse: " + exception.getMessage());
        }
    }

}
