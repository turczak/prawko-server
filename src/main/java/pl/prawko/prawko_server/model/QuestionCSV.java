package pl.prawko.prawko_server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record QuestionCSV(

        @JsonProperty("Nazwa pytania")
        String name,

        @JsonProperty("Numer pytania")
        int id,

        @JsonProperty("Pytanie")
        String contentPL,

        @JsonProperty("Odpowiedź A")
        String answerPL1,

        @JsonProperty("Odpowiedź B")
        String answerPL2,

        @JsonProperty("Odpowiedź C")
        String answerPL3,

        @JsonProperty("Pytanie ENG")
        String contentEN,

        @JsonProperty("Odpowiedź ENG A")
        String answerEN1,

        @JsonProperty("Odpowiedź ENG B")
        String answerEN2,

        @JsonProperty("Odpowiedź ENG C")
        String answerEN3,

        @JsonProperty("Pytanie DE")
        String contentDE,

        @JsonProperty("Odpowiedź DE A")
        String answerDE1,

        @JsonProperty("Odpowiedź DE B")
        String answerDE2,

        @JsonProperty("Odpowiedź DE C")
        String answerDE3,

        @JsonProperty("Poprawna odp")
        char correctAnswer,

        @JsonProperty("Media")
        String mediaName,

        @JsonProperty("Zakres struktury")
        String type,

        @JsonProperty("Liczba punktów")
        int value,

        @JsonProperty("Kategorie")
        String categories

) {

    public Map<String, Map<Character, String>> getAnswersTranslations() {
        return Map.of(
                Language.PL, Map.of(
                        'A', answerPL1,
                        'B', answerPL2,
                        'C', answerPL3
                ),
                Language.EN, Map.of(
                        'A', answerEN1,
                        'B', answerEN2,
                        'C', answerEN3
                ),
                Language.DE, Map.of(
                        'A', answerDE1,
                        'B', answerDE2,
                        'C', answerDE3
                )
        );
    }

}
