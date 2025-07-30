package pl.prawko.prawko_server.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import pl.prawko.prawko_server.service.CategoryService;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CSVMappingUtilTest {

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldMapCsvFileCorrectly() throws IOException {
        final var expected = new QuestionCSVRepresentation(
                "PD10(3)",
                2143,
                "Jak często należy obracać poszkodowanego nieurazowego na drugi bok po ułożeniu go w pozycji bezpiecznej?",
                "Co 60 minut.",
                "Co 30 minut.",
                "Co 15 minut.",
                "How often should you turn a non-traumatic victim to the other side after laying him in the recovery position?",
                "Every 60 minutes.",
                "Every 30 minutes.",
                "Every 15 minutes.",
                "Wie oft soll man einen symptomlosen Betroffenen auf die andere Körperseite nach dem Legen in stabiler Seitenlage drehen?",
                "jede 60 Minuten",
                "jede 30 Minuten",
                "jede 15 Minuten",
                'B',
                "R_101org.jpg",
                "SPECJALISTYCZNY",
                2,
                "PT"
        );
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
        final var result = new CSVMappingUtil(categoryService)
                .csvToRepresentations(file);
        assertThat(result)
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(expected);
    }

}
