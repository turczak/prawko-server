package pl.prawko.prawko_server.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;

@Entity
public class Question {

    @Id
    private long id;

    private String name;
    private int points;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<QuestionTranslation> translations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<Answer> answers;

    @Column(length = 63)
    private String media;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "question_category",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany(mappedBy = "questions", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Exam> exams;

    public Question() {
    }

    public long getId() {
        return id;
    }

    public Question setId(final long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Question setName(final String name) {
        this.name = name;
        return this;
    }

    public int getPoints() {
        return points;
    }

    public Question setPoints(final int points) {
        this.points = points;
        return this;
    }

    public List<QuestionTranslation> getTranslations() {
        return translations;
    }

    public Question setTranslations(final List<QuestionTranslation> translations) {
        this.translations = translations;
        return this;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Question setAnswers(final List<Answer> answers) {
        this.answers = answers;
        return this;
    }

    public String getMedia() {
        return media;
    }

    public Question setMedia(final String media) {
        this.media = media;
        return this;
    }

    public QuestionType getType() {
        return type;
    }

    public Question setType(final QuestionType type) {
        this.type = type;
        return this;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Question setCategories(final List<Category> categories) {
        this.categories = categories;
        return this;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public Question setExams(final List<Exam> exams) {
        this.exams = exams;
        return this;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final var question = (Question) object;
        return id == question.id
                && points == question.points
                && Objects.equals(name, question.name)
                && Objects.equals(translations, question.translations)
                && Objects.equals(answers, question.answers)
                && Objects.equals(media, question.media)
                && type == question.type
                && Objects.equals(categories, question.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, points, translations, answers, media, type, categories);
    }

    @Override
    public String toString() {
        return "Question{id=%d, name='%s', points=%d, translations=%s, answers=%s media='%s', type=%s, categories=%s}"
                .formatted(id, name, points, translations, answers, media, type, categories);
    }

}
