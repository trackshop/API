// XII·IX <> VII·X

package nl.th7mo.trackshop.api.role;

public enum Roles {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    SUPER_ADMIN("ROLE_SUPER_ADMIN");

    public final String value;

    Roles(String value) {
        this.value = value;
    }
}
