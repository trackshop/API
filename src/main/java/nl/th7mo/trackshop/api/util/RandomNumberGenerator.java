// XII·IX <> VII·X

package nl.th7mo.trackshop.api.util;

public final class RandomNumberGenerator {

    public static double getRandomNumber(int minPrice, int maxPrice) {
        double number = Math.random() * (maxPrice - minPrice);

        return Math.round((minPrice + number) * 100) / 100.0;
    }
}
