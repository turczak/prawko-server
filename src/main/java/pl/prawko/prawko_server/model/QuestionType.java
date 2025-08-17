package pl.prawko.prawko_server.model;

import java.util.Arrays;

public enum QuestionType {

    BASIC("PODSTAWOWY"),
    SPECIAL("SPECJALISTYCZNY");

    private final String name;

    QuestionType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static QuestionType ofType(final String name) {
        return Arrays.stream(QuestionType.values())
                .filter(type -> type.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unexpected value: " + name));
    }

}
