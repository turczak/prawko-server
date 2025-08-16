package pl.prawko.prawko_server.model;

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
        return switch (name) {
            case "PODSTAWOWY" -> BASIC;
            case "SPECJALISTYCZNY" -> SPECIAL;
            default -> throw new IllegalStateException("Unexpected value: " + name);
        };
    }

}
