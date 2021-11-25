package personal.springutility.model.todo;

import lombok.Getter;

@Getter
public enum Priority {
    HIGHEST("HIGHEST"),
    IMPORTANT("IMPORTANT"),
    NORMAL("NORMAL"),
    LOW("LOW");

    private final String priority;

    Priority(String priority) {
        this.priority = priority;
    }

    public static Priority of(String priority){
        for(Priority pri : Priority.values()){
            if(pri.priority.equals(priority)){
                return pri;
            }
        }
        return NORMAL;
    }
}
