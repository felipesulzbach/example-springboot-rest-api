package restapi.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

/**
 * @autor: Felipe Sulzbach
 */
public class DataFakeUtil {

    public static String getString(int lengthValue, boolean useNumbers) {
        return generateRandomAlphanumeric(lengthValue, true, useNumbers);
    }

    public static Integer getInteger(int lengthValue) {
        return Integer.valueOf(getIntegerString(lengthValue));
    }

    public static String getIntegerString(int lengthValue) {
        String response = generateRandomAlphanumeric(lengthValue, false, true);

        return response;
    }

    public static Long getLong(int lengthValue) {
        String response = generateRandomAlphanumeric(lengthValue, false, true);

        return Long.parseLong(response);
    }

    public static LocalDateTime getLocalDateTime(LocalDateTime startTime, LocalDateTime endTime) {
        long days = ChronoUnit.DAYS.between(startTime, endTime);
        LocalDateTime randomDate = startTime.plusDays(new Random().nextInt((int) days + 1));
        return randomDate;
    }

    private static String generateRandomAlphanumeric(int lengthValue, boolean useLetters, boolean useNumbers) {
        if (lengthValue <= 0) {
            throw new IllegalArgumentException("The lengthValue value must be greater than zero.");
        }

        String generatedString = RandomStringUtils.random(lengthValue, useLetters, useNumbers);

        return generatedString;
    }
}
