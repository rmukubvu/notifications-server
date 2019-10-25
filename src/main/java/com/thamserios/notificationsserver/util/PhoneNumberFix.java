package com.thamserios.notificationsserver.util;

public class PhoneNumberFix {
    public static String getCorrectPhoneNumber(String countryCode,String msisdn) {
        if (msisdn.startsWith("0"))
            return countryCode + msisdn.substring(1);
        return msisdn;
    }
}
