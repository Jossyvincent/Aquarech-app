package com.aquarech.farmer.app;

import android.content.SharedPreferences;
import android.content.Context;

import com.aquarech.farmer.utils.Config;

public class PreferenceManager {
    private static SharedPreferences prefs;

    public PreferenceManager(Context context) {
        prefs = context.getSharedPreferences(Config.PREF_NAME, Context.MODE_PRIVATE);
    }

    // intro
    public static void setHasSeenIntro(boolean value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(Config.KEY_HAS_SEEN_INTRO, value);
        editor.apply();
    }
    public static boolean hasSeenIntro() {
        return prefs.getBoolean(Config.KEY_HAS_SEEN_INTRO, false);
    }
}
