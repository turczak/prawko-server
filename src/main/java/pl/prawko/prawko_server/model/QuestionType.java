package pl.prawko.prawko_server.model;

import java.util.Arrays;

/**
 * Represents the type of question.
 * <p>
 * Each type is associated with a descriptive name in polish because that's how it's stored in original CSV file from
 * <a href="https://www.gov.pl/web/infrastruktura/prawo-jazdy">Officials questions for polish driving licence test</a>.
 */
public enum QuestionType {

    /**
     * True/False question type
     */
    BASIC("PODSTAWOWY"),
    /**
     * ABC question type
     */
    SPECIAL("SPECJALISTYCZNY");

    private final String name;

    QuestionType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns corresponding {@code QuestionType} based on the given name.
     *
     * @param name the name of the question type
     * @return the matching {@code QuestionType}
     * @throws IllegalStateException if the given name does not correspond to any known type
     */
    public static QuestionType ofType(final String name) {
        return Arrays.stream(QuestionType.values())
                .filter(type -> type.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unexpected value: " + name));
    }

}
