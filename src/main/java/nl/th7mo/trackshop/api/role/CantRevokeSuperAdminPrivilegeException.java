// XII·IX <> VII·X

package nl.th7mo.trackshop.api.role;

public class CantRevokeSuperAdminPrivilegeException extends IllegalArgumentException {

    public CantRevokeSuperAdminPrivilegeException(String errorMessage) {
        super(errorMessage);
    }
}
