package com.example.conference.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator class
 */
public final class Validator {
    private Validator() {
    }
    /**
     * Email regex, where there is no less than 1 letter before "@", 2 letters after,
     * and domain is no longer than 4 characters.
     */

    private static final String EMAIL_REGEX = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";

    /**
     * Regular expression for all alphanumeric characters and predefined wild characters,
     * must consist of at least 4 characters and not more than 16 characters.
     */

    private static final String PASSWORD_REGEX = "^([a-zA-Z0-9@*#]{4,16})$";

    /**
     * Regular expression for phone,
     * must starts with + and has 9 digits.
     */

    private static final String PHONE_REGEX = "^\\+\\d{9}$";

    /**
     * Regular expression for a positive integer.
     */

    private static final String ID_REGEX = "(\\d|[1-9]\\d{1,8}|1\\d{9}|20\\d{8}|21[0-3]\\d{7}|214[0-6]" +
            "\\d{6}|2147[0-3]\\d{5}|21474[0-7]\\d{4}|214748[0-2]\\d{3}|2147483[0-5]\\d{2}|21474836[0-3]" +
            "\\d|214748364[0-7])";

    /**
     * Regular expression for first/last name (hyphen is allowed) starting with a capital letter
     * and no more than 25 letters long.
     */

    private static final String NAME_REGEX = "^[A-ZА-Я][\\p{Alpha}А-Яа-я\\s-]{1,25}";

    /**
     * Regular expression for text up to 255 characters long.
     */

    private static final String TEXT_REGEX = "^.{0,255}$";


    /**
     * Regular expression for YYYY-MM-DD dates.
     */

    private static final String DATE_REGEX = "^(19|20)\\d\\d([-])(0[1-9]|1[012])\\2(0[1-9]|[12][0-9]|3[01])$";

    public static boolean isValidEmail(String email) {
        boolean isValid;
        if (!email.isBlank()) {
            Pattern pattern = Pattern.compile(EMAIL_REGEX);
            Matcher matcher = pattern.matcher(email);
            isValid = matcher.matches();
        } else {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidPassword(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }
        return password.matches(PASSWORD_REGEX);
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.isBlank()) {
            return false;
        }
        return phone.matches(PHONE_REGEX);
    }

    public static boolean isValidId(String id) {
        if (id == null || id.isBlank()) {
            return false;
        }
        return id.matches(ID_REGEX);
    }

    public static boolean isValidName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        return name.matches(NAME_REGEX);
    }

    public static boolean isValidText(String text) {
        if (text == null || text.isBlank()) {
            return false;
        }
        return text.matches(TEXT_REGEX);
    }

    public static boolean isValidDateFormat(String date) {
        if (date == null || date.isBlank()) {
            return false;
        }
        return date.matches(DATE_REGEX);
    }
}
