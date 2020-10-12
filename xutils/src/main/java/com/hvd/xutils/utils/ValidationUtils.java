package com.hvd.xutils.utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    public static boolean isValidString(String string){
        return string != null && !string.isEmpty();
    }

    public static boolean isValidPostalCode(String postalCode) {
        return postalCode != null && postalCode.length() == 10;
    }

    public static boolean isValidSheba(String sheba) {
        return sheba != null && sheba.length() == 24;
    }


    public static boolean isValidNationalCode(String melliCode) {

        String[] identicalDigits = {"0000000000", "1111111111", "2222222222", "3333333333", "4444444444", "5555555555", "6666666666", "7777777777", "8888888888", "9999999999"};

        if (melliCode.trim().isEmpty()) {
            return false; // Melli Code is empty
        } else if (melliCode.length() != 10) {
            return false; // Melli Code is less or more than 10 digits
        } else if (Arrays.asList(identicalDigits).contains(melliCode)) {
            return false; // Fake Melli Code
        } else {
            int sum = 0;

            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(melliCode.charAt(i)) * (10 - i);
            }

            int lastDigit;
            int divideRemaining = sum % 11;

            if (divideRemaining < 2) {
                lastDigit = divideRemaining;
            } else {
                lastDigit = 11 - (divideRemaining);
            }

            if (Character.getNumericValue(melliCode.charAt(9)) == lastDigit) {
                return true;
            } else {
                return false; // Invalid MelliCode
            }
        }
    }

    public static boolean isValidIranTel(String tel) {
        if (tel == null) {
            return false;
        }
        Pattern pattern;
        Matcher matcher;
        String TEL_PATTERN = "^(\\+98|0)9\\d{9}$";
//        String TEL_PATTERN = "^(09|۰۹)([0-9]|[۰-۹]){9}$";
        pattern = Pattern.compile(TEL_PATTERN);
        matcher = pattern.matcher(tel);
        return matcher.matches();
    }


    public static boolean isValidEmail(String email) {
        Pattern EMAIL_PATTERN =
                Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

}
