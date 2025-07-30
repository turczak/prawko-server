package pl.prawko.prawko_server.dto;

import pl.prawko.prawko_server.model.AnswerTranslation;

import java.util.List;

public record AnswerDto(

        long id,
        long questionId,
        boolean correct,
        char label,
        String content,
        List<AnswerTranslation> translations

) {
}
