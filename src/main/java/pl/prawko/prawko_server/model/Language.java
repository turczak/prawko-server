package pl.prawko.prawko_server.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;

@Entity
public class Language {

    public static final String PL = "pl";
    public static final String EN = "en";
    public static final String DE = "de";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 31)
    private String name;

    @Column(length = 2)
    private String code;

    @Column(length = 3)
    private String icon;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language")
    private List<QuestionTranslation> questionTranslations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language")
    private List<AnswerTranslation> answerTranslations;

    @OneToMany(mappedBy = "language")
    private List<Exam> exams;

    public long getId() {
        return id;
    }

    public Language setId(final long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Language setName(final String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Language setCode(final String code) {
        this.code = code;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public Language setIcon(final String icon) {
        this.icon = icon;
        return this;
    }

    public List<QuestionTranslation> getQuestionTranslations() {
        return questionTranslations;
    }

    public Language setQuestionTranslations(final List<QuestionTranslation> questionTranslations) {
        this.questionTranslations = questionTranslations;
        return this;
    }

    public List<AnswerTranslation> getAnswerTranslations() {
        return answerTranslations;
    }

    public Language setAnswerTranslations(final List<AnswerTranslation> answerTranslations) {
        this.answerTranslations = answerTranslations;
        return this;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public Language setExams(List<Exam> exams) {
        this.exams = exams;
        return this;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final var language = (Language) object;
        return id == language.id
                && Objects.equals(name, language.name)
                && Objects.equals(code, language.code)
                && Objects.equals(icon, language.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, icon);
    }

    @Override
    public String toString() {
        return "Language{id=%d, name='%s', code='%s', icon='%s'}"
                .formatted(id, name, code, icon);
    }

}
