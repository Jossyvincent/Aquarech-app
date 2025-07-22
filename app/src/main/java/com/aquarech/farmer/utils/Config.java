package com.aquarech.farmer.utils;

import java.util.regex.Pattern;

public class Config {
    // timeout for all animations
    public static final int SPLASH_DELAY = 3000;

    // phone number regex pattern
    public static final Pattern KENYAN_PHONE_PATTERN = Pattern.compile(
            "^(?:\\+254|254|0)?7\\d{8}$"
    );
}
