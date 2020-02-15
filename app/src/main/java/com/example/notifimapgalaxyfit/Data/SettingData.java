package com.example.notifimapgalaxyfit.Data;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingData {

    private static final String SP_NAME = "Setting";
    private static final String SP_D_VIBRATE = "Setting";
//    private static final String SP_NAME = "Setting";

    public static void setVibrate(Context context, boolean isVibrate) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SP_D_VIBRATE, isVibrate).apply();

    }

    public static boolean getVibrate(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(SP_D_VIBRATE, false);
    }


}
