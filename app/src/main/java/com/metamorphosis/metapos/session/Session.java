package com.metamorphosis.metapos.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.metamorphosis.metapos.Activity.LogInActivity;
import com.metamorphosis.metapos.Utils.AppConstant;

/**
 * Created by metakave-android-PC on 30-May-19.
 */

public class Session {

    /**
     * @param context
     * @param key
     * @return
     */
    public static boolean saveDataAtSharePreference(Context context, String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
        editor.commit();

        return true;
    }

    public static boolean saveDataAtSharePreference(Context context, String key, boolean value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
        editor.commit();

        return true;
    }


    /**
     * @param context
     * @param key
     * @return
     */
    public static boolean saveDataAtSharePreference(Context context, String key, Float value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value);
        editor.apply();
        editor.commit();

        return true;
    }

    /**
     * @param context
     * @param key
     * @return
     */
    public static boolean saveDataAtSharePreference(Context context, String key, Integer value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
        editor.commit();

        return true;
    }

    /**
     * @param context
     * @param key
     * @return
     */
    public static String getStringFromSharePreference(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        String basefolderpath = preferences.getString(key, null);

        return basefolderpath;
    }

    /**
     * @param context
     * @param key
     * @return
     */
    public static Float getFloatFromSharePreference(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        Float basefolderpath = preferences.getFloat(key, 0);

        return basefolderpath;
    }

    /**
     * @param context
     * @param key
     * @return
     */
    public static Integer getIntFromSharePreference(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        Integer basefolderpath = preferences.getInt(key, 0);

        return basefolderpath;
    }


    public static Boolean getBooleanFromSharePreference(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        Boolean basefolderpath = preferences.getBoolean(key, false);
        if (basefolderpath == true) {
            return basefolderpath;
        }
        return false;
    }


    public static void logoutUser(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        // Clearing all data from Shared Preferences

        editor.remove(AppConstant.IS_LOGGED_IN);

        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(context, LogInActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring Login Activity
        context.startActivity(i);
    }


    public static void removeData(Context context, String tag) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        // Clearing all data from Shared Preferences
        // editor.clear();
        editor.remove(tag);
        editor.commit();
    }

}
