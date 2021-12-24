// XII·IX <> VII·X

package nl.th7mo.trackshop.api.user;

import java.nio.file.FileAlreadyExistsException;

public class UserAlreadyExistsException extends FileAlreadyExistsException {

    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
