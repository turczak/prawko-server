package pl.prawko.prawko_server.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private char label;
    private boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<AnswerTranslation> translations;

    @ManyToMany(mappedBy = "userAnswers", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Exam> tests;

    public Answer() {
    }

    public long getId() {
        return id;
    }

    public Answer setId(final long id) {
        this.id = id;
        return this;
    }

    public char getLabel() {
        return label;
    }

    public Answer setLabel(final char label) {
        this.label = label;
        return this;
    }

    public boolean isCorrect() {
        return correct;
    }

    public Answer setCorrect(final boolean correct) {
        this.correct = correct;
        return this;

    }

    public Question getQuestion() {
        return question;
    }

    public Answer setQuestion(final Question question) {
        this.question = question;
        return this;
    }

    public List<AnswerTranslation> getTranslations() {
        return translations;
    }

    public Answer setTranslations(final List<AnswerTranslation> translations) {
        this.translations = translations;
        return this;
    }

    public List<Exam> getTests() {
        return tests;
    }

    public Answer setTests(final List<Exam> tests) {
        this.tests = tests;
        return this;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final var answer = (Answer) object;
        return id == answer.id
                && label == answer.label
                && correct == answer.correct
                && Objects.equals(translations, answer.translations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, correct, translations);
    }

    @Override
    public String toString() {
        return "Answer{id=%d, label=%s, correct=%s, translations=%s}"
                .formatted(id, label, correct, translations);
    }

}
