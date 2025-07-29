package com.aquarech.farmer.utils;

import java.util.regex.Pattern;

public class Config {
    // timeout for all animations
    public static final int SPLASH_DELAY = 3000;

    // phone number regex pattern
    public static final Pattern KENYAN_PHONE_PATTERN = Pattern.compile(
            "^(?:\\+254|254|0)?(7|1)\\d{8}$"
    );
    // preferenceManager constants
    public static final String PREF_NAME = "aquarech_prefs";
    public static final String KEY_HAS_SEEN_INTRO = "has_seen_intro";

    // Database constants
    public static final String DATABASE_NAME= "Aquarech.db";
    public static final int DATABASE_VERSION = 1;

    // user table constants
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_PASSWORD = "password";

}
