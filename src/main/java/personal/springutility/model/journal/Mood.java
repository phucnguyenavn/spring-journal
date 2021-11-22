package personal.springutility.model.journal;


import lombok.Getter;

@Getter
public enum Mood {

    NO_RATING(0),
    HORRIBLE(1),
    BAD(2),
    OK(3),
    GOOD(4),
    AWESOME(5);

    private final int value;

     Mood(int value) {
        this.value = value;
    }

    public static Mood of(Integer mood) {
        for (Mood r : values()) {
            if (r.getValue() == mood) {
                return r;
            }
        }
        return NO_RATING;
    }
}
