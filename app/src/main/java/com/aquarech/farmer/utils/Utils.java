package com.aquarech.farmer.utils;

public class Utils {
    public static boolean isValidPhoneNumber(String phoneNumber){
        if(phoneNumber == null)
            return false;

        phoneNumber = phoneNumber.replaceAll("\\s+", "");
        return Config.KENYAN_PHONE_PATTERN.matcher(phoneNumber).matches();
    }
}
