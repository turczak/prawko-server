package pl.prawko.prawko_server.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import pl.prawko.prawko_server.mapper.CSVMapper;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CSVMappingUtilTest {

    @Mock
    private CSVMapper CSVMapper;

    private CSVMappingUtil csvMappingUtil;

    @BeforeEach
    void setUp() {
        csvMappingUtil = new CSVMappingUtil(CSVMapper);
    }

    @Test
    void mapCSVFile_success() throws IOException {
        final ClassPathResource resource = new ClassPathResource("test_question.csv");
        assertThat(resource)
                .satisfies(res -> {
                    assertThat(res.exists()).isTrue();
                    assertThat(res.isReadable()).isTrue();
                });
        final var inputStream = resource.getInputStream();
        final var file = new MockMultipartFile(
                "file",
                "test_question.csv",
                "text/csv",
                inputStream);
        final var result = csvMappingUtil.mapFileToQuestionCSVModels(file);
        assertThat(result)
                .hasSize(1)
                .first()
                .isEqualTo(TestDataUtil.QUESTION_CSV);
    }

}
