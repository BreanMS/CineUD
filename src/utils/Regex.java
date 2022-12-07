package utils;

import java.util.regex.Pattern;

public class Regex {
    /**
     * Checks if the "input" string respects this format:
     * first letter capitalized, then small letters.<br>
     * Minimum length is 2, maximum is 25.
     * @param input - the string to be verified
     * @return matching result
     */
    public static boolean matchName(String input) {
        return Pattern.matches("[A-Z][a-z]{1,24}", input);
    }

    /**
     * Checks if the "input" string contains just letters and spaces.<br>
     * It's called "matchFreeName" because it's used at ticket details, where
     * a customer can enter more than one name (first name + middle names for example).<br>
     * It's not used in security, so it can have a little bit of freedom.<br>
     * Minimum length is 2, maximum is 30.
     * @param input - the string to be verified
     * @return matching result
     */
    public static boolean matchFreeName(String input) {
        return Pattern.matches("[A-Za-z\\d ]{1,29}", input);
    }

    /**
     * Checks if the "input" string contains just a-zA-Z0-9.<br>
     * Minimum length is 6, maximum is 15.
     * @param input - the string to be verified
     * @return matching result
     */
    public static boolean matchPassword(String input) {
        return Pattern.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])([a-zA-Z\\d]{6,15}))", input);
    }

    /**
     * Checks if the "input" string contains just a-zA-Z0-9- and spaces.<br>
     * Minimum length is 2, maximum is 25.
     * @param input - the string to be verified
     * @return matching result
     */
    public static boolean matchFlightClass(String input) {
        return Pattern.matches("(([a-zA-Z\\d- ]{2,25}))", input);
    }

    /**
     * Checks if the "input" string contains just 0-9.<br>
     * If "input" has more than 1 number (char), checks if the first one is 0.<br>
     * By doing that, eliminates possible bad data.<br>
     * Minimum length is 1, maximum is 4.
     * @param input - the string to be verified
     * @return matching result
     */
    public static boolean matchSeatsNo(String input) {
        if (input.length() > 1) {
            return Pattern.matches("[1-9][\\d]{1,3}", input);
        } else {
            return Pattern.matches("[\\d]", input);
        }
    }

    /**
     * Checks if the "input" string contains just numbers from 1-9999.<br>
     * If "input" has more than 1 number (char), checks if the first one is 0.<br>
     * By doing that, eliminates possible bad data.<br>
     * @param input - the string to be verified
     * @return matching result
     */
    public static boolean matchClientSeatsNo(String input) {
        if (input.length() > 1) {
            return Pattern.matches("[1-9][\\d]{1,3}", input);
        } else {
            return Pattern.matches("[1-9]", input);
        }
    }

    /**
     * Checks if the "input" price contains just numbers.<br>
     * If "input" has more than 1 number (char), checks if the first one is 0.<br>
     * By doing that, eliminates possible bad data.<br>
     * Minimum length is 1, maximum is 5.<br>
     * Method used for checking the full price of a plane ticket (100%).
     * @param input - the string to be verified
     * @return matching result
     */
    public static boolean matchFullPrice(String input) {
        if (input.length() > 1) {
            return Pattern.matches("[1-9][\\d]{1,4}", input);
        } else {
            return Pattern.matches("[\\d]", input);
        }
    }

    /**
     * Checks if input contains just 2 digits.
     * @param input - the string to be verified
     * @return mathing result
     */
    public static boolean matchDoubleDigit(String input) {
        return Pattern.matches("[\\d]{2}", input);
    }
}
