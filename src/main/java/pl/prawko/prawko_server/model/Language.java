package pl.prawko.prawko_server.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

@Entity
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 31)
    private String name;

    @Column(length = 3)
    private String code;

    @Column(length = 3)
    private String icon;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language")
    private List<QuestionTranslation> questionTranslations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language")
    private List<AnswerTranslation> answerTranslations;

    @OneToMany(mappedBy = "language")
    private List<User> users;

}
