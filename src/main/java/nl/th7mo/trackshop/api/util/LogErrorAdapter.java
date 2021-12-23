// XII·IX <> VII·X

package nl.th7mo.trackshop.api.util;

import java.util.logging.Logger;
import java.util.logging.Level;

public final class LogErrorAdapter {

    private static final Logger logger = Logger.getLogger("ExceptionLogger");

    public static <T> void log(Class<T> exceptionClass) {
        String errorMessage = exceptionClass.getSimpleName() + " thrown!";
        logger.log(Level.WARNING, errorMessage);
    }
}
