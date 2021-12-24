// XII·IX <> VII·X

package nl.th7mo.trackshop.api.user;

import java.io.FileNotFoundException;

public class UserNotFoundException extends FileNotFoundException {

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
