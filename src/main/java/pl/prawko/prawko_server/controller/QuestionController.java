package pl.prawko.prawko_server.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import pl.prawko.prawko_server.dto.ApiResponse;
import pl.prawko.prawko_server.service.implementation.QuestionService;

/**
 * REST controller to managing questions using {@code http requests} mapped on {@code /questions}.
 */
@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * {@code POST} for upload questions from CSV file.
     * <p>
     * It's parsing provided file to questions which will be saved.
     * <p>
     * Errors are handled using {@link ExceptionController#handleMissingFile(MissingServletRequestPartException)}
     * and {@link ExceptionController#handleWrongFileType(MultipartException)}.
     *
     * @param file provided file in request
     * @return success if questions from file were added
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> addQuestions(@RequestPart MultipartFile file) {
        final var questions = questionService.parseFileToQuestions(file);
        questionService.saveAll(questions);
        return ResponseEntity.ok()
                .body(new ApiResponse("Questions from file added successfully."));
    }

}
