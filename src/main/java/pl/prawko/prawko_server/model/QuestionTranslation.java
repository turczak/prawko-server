package pl.prawko.prawko_server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class QuestionTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    public QuestionTranslation() {
    }

    public long getId() {
        return id;
    }

    public QuestionTranslation setId(final long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public QuestionTranslation setContent(final String content) {
        this.content = content;
        return this;
    }

    public Question getQuestion() {
        return question;
    }

    public QuestionTranslation setQuestion(final Question question) {
        this.question = question;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public QuestionTranslation setLanguage(final Language language) {
        this.language = language;
        return this;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final var that = (QuestionTranslation) object;
        return id == that.id
                && Objects.equals(content, that.content)
                && Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, language);
    }

    @Override
    public String toString() {
        return "QuestionTranslation{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", language=" + language +
                '}';
    }

}
