package pl.prawko.prawko_server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record QuestionCSV(

        @JsonProperty("Nazwa pytania")
        String name,

        @JsonProperty("Numer pytania")
        int id,

        @JsonProperty("Pytanie")
        String content_pol,

        @JsonProperty("Odpowiedź A")
        String answer_A_pol,

        @JsonProperty("Odpowiedź B")
        String answer_B_pol,

        @JsonProperty("Odpowiedź C")
        String answer_C_pol,

        @JsonProperty("Pytanie ENG")
        String content_eng,

        @JsonProperty("Odpowiedź ENG A")
        String answer_A_eng,

        @JsonProperty("Odpowiedź ENG B")
        String answer_B_eng,

        @JsonProperty("Odpowiedź ENG C")
        String answer_C_eng,

        @JsonProperty("Pytanie DE")
        String content_ger,

        @JsonProperty("Odpowiedź DE A")
        String answer_A_ger,

        @JsonProperty("Odpowiedź DE B")
        String answer_B_ger,

        @JsonProperty("Odpowiedź DE C")
        String answer_C_ger,

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
                "pol", Map.of(
                        'A', answer_A_pol,
                        'B', answer_B_pol,
                        'C', answer_C_pol
                ),
                "eng", Map.of(
                        'A', answer_A_eng,
                        'B', answer_B_eng,
                        'C', answer_C_eng
                ),
                "ger", Map.of(
                        'A', answer_A_ger,
                        'B', answer_B_ger,
                        'C', answer_C_ger
                )
        );
    }

}
