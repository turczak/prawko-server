package pl.prawko.prawko_server.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 3)
    private String name;

    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
    private List<Question> questions;

    @OneToMany(mappedBy = "category")
    private List<Exam> exams;

    public long getId() {
        return id;
    }

    public Category setId(final long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Category setName(final String name) {
        this.name = name;
        return this;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Category setQuestions(final List<Question> questions) {
        this.questions = questions;
        return this;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public Category setExams(List<Exam> exams) {
        this.exams = exams;
        return this;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final var category = (Category) object;
        return id == category.id
                && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
