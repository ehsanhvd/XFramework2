package com.hvd.xutils.utils;

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

}
