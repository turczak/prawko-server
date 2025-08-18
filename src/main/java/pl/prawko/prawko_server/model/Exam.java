package pl.prawko.prawko_server.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinTable(
            name = "test_question",
            joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinTable(
            name = "test_answer",
            joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id")
    )
    private List<Answer> userAnswers;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private boolean active;
    private int score;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Exam setCreated(final LocalDateTime created) {
        this.created = created;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Exam setUser(final User user) {
        this.user = user;
        return this;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Exam setQuestions(final List<Question> questions) {
        this.questions = questions;
        return this;
    }

    public List<Answer> getUserAnswers() {
        return userAnswers;
    }

    public Exam setUserAnswers(final List<Answer> userAnswers) {
        this.userAnswers = userAnswers;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Exam setActive(final boolean active) {
        this.active = active;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Exam setScore(final int score) {
        this.score = score;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public Exam setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Exam setCategory(Category category) {
        this.category = category;
        return this;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final var exam = (Exam) object;
        return id == exam.id
                && Objects.equals(user, exam.user)
                && Objects.equals(questions, exam.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, questions);
    }

    @Override
    public String toString() {
        return "Exam{id=%d, created=%s, user=%s, active=%s, score=%d, questions=%s, userAnswers=%s}"
                .formatted(id, created, user, active, score, questions, userAnswers);
    }

}
