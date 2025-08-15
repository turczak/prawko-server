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
public class AnswerTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    public AnswerTranslation() {
    }

    public AnswerTranslation(final Answer answer,
                             final Language language,
                             final String content) {
        this.content = content;
        this.answer = answer;
        this.language = language;
    }

    public long getId() {
        return id;
    }

    public AnswerTranslation setId(final long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public AnswerTranslation setContent(final String content) {
        this.content = content;
        return this;
    }

    public Answer getAnswer() {
        return answer;
    }

    public AnswerTranslation setAnswer(final Answer answer) {
        this.answer = answer;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public AnswerTranslation setLanguage(final Language language) {
        this.language = language;
        return this;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final var that = (AnswerTranslation) object;
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
        return "AnswerTranslation{id=%d, content='%s', language=%s}"
                .formatted(id, content, language);
    }

}
