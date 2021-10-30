package personal.springutility.model.journal;


import lombok.Getter;

@Getter
public enum RatingScale {

    NO_RATING,
    HORRIBLE,
    BAD,
    OK,
    GOOD,
    AWESOME;

    public static RatingScale of(String scale) {
        for (RatingScale r : values()) {
            if (r.name().equals(scale)) {
                return r;
            }
        }
        return NO_RATING;
    }
}
