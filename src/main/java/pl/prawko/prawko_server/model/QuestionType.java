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
        for (QuestionType type : QuestionType.values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        throw new IllegalStateException("Unexpected value: " + name);
    }

}
