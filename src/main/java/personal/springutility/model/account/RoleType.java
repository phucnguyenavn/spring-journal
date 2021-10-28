package personal.springutility.model.account;

public enum RoleType {

    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    RoleType(String role) {
        this.role = role;
    }
}
