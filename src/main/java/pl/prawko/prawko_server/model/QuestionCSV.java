package pl.prawko.prawko_server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Represents single question from CSV file.
 *
 * @param name          name of a question
 * @param id            fixed identifier
 * @param contentPL     translation of a question content in Polish
 * @param answerPL1     content of first answer in Polish
 * @param answerPL2     content of second answer in Polish
 * @param answerPL3     content of third answer in Polish
 * @param contentEN     translation of question content in English
 * @param answerEN1     content of first answer translation in English
 * @param answerEN2     content of second answer translation in English
 * @param answerEN3     content of third answer translation in English
 * @param contentDE     translation of a question content in German
 * @param answerDE1     content of first answer translation in German
 * @param answerDE2     content of second answer translation in German
 * @param answerDE3     content of third answer translation in German
 * @param correctAnswer label of correct answer - {@code A}, {@code B}, {@code C}
 *                      and {@code T}, {@code N} which is Polish translation of {@code Yes}, {@code No}
 *                      which stands for {@code True} and {@code False}
 * @param mediaName     filename of media attached to a question - {@code .wmv} | {@code .jpg}
 * @param type          question type - {@code PODSTAWOWY} or {@code SPECJALISTYCZNY}
 * @param value         score value of correct answer to a question
 * @param categories    categories to which the question belongs represented by comma-separated string
 */
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

    /**
     * Returns all available translations of the answers.
     *
     * @return a map where the key is a {@link Language} and the value is another map of answer labels to their translated content
     */
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
