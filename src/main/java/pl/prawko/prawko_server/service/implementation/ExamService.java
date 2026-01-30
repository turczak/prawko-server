package pl.prawko.prawko_server.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.prawko.prawko_server.dto.ExamDto;
import pl.prawko.prawko_server.mapper.ExamMapper;
import pl.prawko.prawko_server.model.Category;
import pl.prawko.prawko_server.model.Exam;
import pl.prawko.prawko_server.model.Question;
import pl.prawko.prawko_server.model.QuestionType;
import pl.prawko.prawko_server.repository.ExamRepository;
import pl.prawko.prawko_server.service.IExamService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of {@link IExamService} that manages an {@link Exam} entities.
 */
@Service
public class ExamService implements IExamService {

    /**
     * key - points value of questions
     * value - amount of questions
     */
    private static final Map<QuestionType, Map<Integer, Integer>> QUESTIONS_DISTRIBUTION = Map.of(
            QuestionType.BASIC, Map.of(
                    1, 4,
                    2, 6,
                    3, 10
            ),
            QuestionType.SPECIAL, Map.of(
                    1, 2,
                    2, 4,
                    3, 6
            )
    );

    private final ExamRepository repository;
    private final UserService userService;
    private final QuestionService questionService;
    private final CategoryService categoryService;
    private final ExamMapper examMapper;

    public ExamService(final ExamRepository repository,
                       final UserService userService,
                       final QuestionService questionService,
                       final CategoryService categoryService,
                       final ExamMapper examMapper) {
        this.repository = repository;
        this.userService = userService;
        this.questionService = questionService;
        this.categoryService = categoryService;
        this.examMapper = examMapper;
    }

    /**
     * {@inheritDoc}
     *
     * @throws EntityNotFoundException when user or category have not been found
     */
    @Override
    @Transactional
    public Exam createExam(final long userId, final String categoryName) {
        final var user = userService.getById(userId);
        final var category = categoryService.findByName(categoryName);
        final var questions = Stream.of(
                        generateQuestions(category, QuestionType.BASIC),
                        generateQuestions(category, QuestionType.SPECIAL))
                .flatMap(Collection::stream)
                .toList();
        final var exam = new Exam()
                .setUser(user)
                .setQuestions(questions)
                .setCategory(category)
                .setScore(0)
                .setActive(true)
                .setUserAnswers(Collections.emptyList());
        user.getExams().add(exam);
        repository.save(exam);
        return exam;
    }

    @Override
    @Transactional
    public ExamDto getById(long examId) {
        final var exam = repository.findById(examId)
                .orElseThrow(() -> new EntityNotFoundException("Exam with '" + examId + "' not found."));
        return examMapper.toDto(exam);
    }

    private List<Question> selectRandomQuestions(final List<Question> questions, final int count) {
        var copy = new ArrayList<>(questions);
        Collections.shuffle(copy);
        return copy.subList(0, Math.min(count, copy.size()));
    }

    private List<Question> generateQuestions(final Category category,
                                             final QuestionType questionType) {
        final var distribution = QUESTIONS_DISTRIBUTION.get(questionType);
        final Map<Integer, List<Question>> questions = questionService.getAllByTypeAndCategory(questionType, category.getName())
                .stream()
                .collect(Collectors.groupingBy(Question::getPoints));
        return distribution.entrySet().stream()
                .flatMap(entry ->
                        selectRandomQuestions(questions.getOrDefault(entry.getKey(), Collections.emptyList()), entry.getValue())
                                .stream())
                .toList();
    }

}
