package school.raikes.Q.utility;

import java.util.Random;

public class RandomUtility {

    public static String generateRandomAlphabeticString(int length) {
        StringBuilder toReturn = new StringBuilder();

        Random r = new Random();

        for (int i = 0; i < length; i++) {
            int rValue = r.nextInt(26);

            char c = (char) (rValue + 65);

            toReturn.append(c);
        }

        return toReturn.toString();
    }
}
