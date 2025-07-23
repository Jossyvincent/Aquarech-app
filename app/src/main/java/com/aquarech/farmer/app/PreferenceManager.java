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

    // methods to handle account creation and save
    public static void saveAccount(String phone,String password){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("user_phone",phone);
        editor.putString("user_password",password);
        editor.putBoolean("has_account", true);
        editor.apply();
    }
    public static boolean hasAccount(){
        return prefs.getBoolean("has_account",false);
    }
    public static String getPhone(){
        return prefs.getString("user_phone",null);
    }
    public static String getPassword(){
        return prefs.getString("user_password",null);
    }
}
