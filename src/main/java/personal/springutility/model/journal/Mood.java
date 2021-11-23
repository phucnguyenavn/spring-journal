package personal.springutility.model.journal;


import lombok.Getter;

@Getter
public enum Mood {

    NO_RATING,
    HORRIBLE,
    BAD,
    OK,
    GOOD,
    AWESOME;


    public static Mood of(String mood) {
        for (Mood r : values()) {
            if (r.name().equals(mood)) {
                return r;
            }
        }
        return NO_RATING;
    }
}
