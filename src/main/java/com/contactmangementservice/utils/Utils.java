package com.contactmangementservice.utils;

import org.springframework.util.StringUtils;

public class Utils {

    public static boolean isNewValue(String oldValue, String newValue) {
        return StringUtils.hasText(newValue) && !newValue.equalsIgnoreCase(oldValue);
    }

}
