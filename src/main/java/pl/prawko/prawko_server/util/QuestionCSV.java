package pl.prawko.prawko_server.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public record QuestionCSV(

        @JsonProperty("Nazwa pytania")
        String name,

        @JsonProperty("Numer pytania")
        int id,

        @JsonProperty("Pytanie")
        String content_PL,

        @JsonProperty("Odpowiedź A")
        String answer_A_PL,

        @JsonProperty("Odpowiedź B")
        String answer_B_PL,

        @JsonProperty("Odpowiedź C")
        String answer_C_PL,

        @JsonProperty("Pytanie ENG")
        String content_EN,

        @JsonProperty("Odpowiedź ENG A")
        String answer_A_EN,

        @JsonProperty("Odpowiedź ENG B")
        String answer_B_EN,

        @JsonProperty("Odpowiedź ENG C")
        String answer_C_EN,

        @JsonProperty("Pytanie DE")
        String content_DE,

        @JsonProperty("Odpowiedź DE A")
        String answer_A_DE,

        @JsonProperty("Odpowiedź DE B")
        String answer_B_DE,

        @JsonProperty("Odpowiedź DE C")
        String answer_C_DE,

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
}
