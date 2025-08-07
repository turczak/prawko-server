package pl.prawko.prawko_server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestionType {

    BASIC("PODSTAWOWY"),
    SPECIAL("SPECJALISTYCZNY");

    private final String name;

}
