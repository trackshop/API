// XII·IX <> VII·X

package nl.th7mo.trackshop.api.util;

import io.github.cdimascio.dotenv.Dotenv;

public final class DotenvAdapter {

    private static final Dotenv dotenv = Dotenv.load();

    public static String get(String key)
    throws NullPointerException {
        String value = dotenv.get(key);

        if (valueNotFound(value)) {
            throw new NullPointerException(
                "value of '" + key + "' not found in .env file"
            );
        }

        return value;
    }

    private static boolean valueNotFound(String value) {
        return value == null;
    }
}
