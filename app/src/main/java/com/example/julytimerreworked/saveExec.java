package com.example.julytimerreworked;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class saveExec {
    public static void save(JulyTimersave toSave, Context context) {
        SharedPreferences sharedPref;
        sharedPref = context.getSharedPreferences("JulyTimer", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("startTime", toSave.getStartTime());

        editor.putString("endTime", toSave.getEndTime());

        editor.putString("brightColorScheme[0]", toSave.getBrightColorScheme()[0]);
        editor.putString("brightColorScheme[1]", toSave.getBrightColorScheme()[1]);
        editor.putString("brightColorScheme[2]", toSave.getBrightColorScheme()[2]);

        editor.putString("darkColorScheme[0]", toSave.getDarkColorScheme()[0]);
        editor.putString("darkColorScheme[1]", toSave.getDarkColorScheme()[1]);
        editor.putString("darkColorScheme[2]", toSave.getDarkColorScheme()[2]);

        editor.putBoolean("show[0]", toSave.getShow()[0]);
        editor.putBoolean("show[1]", toSave.getShow()[1]);
        editor.putBoolean("show[2]", toSave.getShow()[2]);
        editor.putBoolean("show[3]", toSave.getShow()[3]);

        editor.putInt("darkMode[0]", toSave.getDarkMode()[0]);
        editor.putInt("darkMode[1]", toSave.getDarkMode()[1]);
        editor.putInt("darkMode[2]", toSave.getDarkMode()[2]);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if(toSave.getBackgroundImage() != null) {
            toSave.getBackgroundImage().compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] imageInByte = stream.toByteArray();
            String byteArrayString = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                byteArrayString = Base64.getEncoder().encodeToString(imageInByte);
            }
            editor.putString("BackgroundImage", byteArrayString);
        } else {
            editor.putString("BackgroundImage", "kein BG gesetzt.");
        }

        editor.apply();
    }

    public static JulyTimersave load(Context context) {
        SharedPreferences sharedPref;
        sharedPref = context.getSharedPreferences("JulyTimer", Context.MODE_PRIVATE);

        String startTime = sharedPref.getString("startTime", "2023-01-01 05:10:00.000");

        String endTime =   sharedPref.getString("endTime", "2024-01-01 05:10:00.000");

        String[] brightColorScheme = new String[3];
        brightColorScheme[0] = sharedPref.getString("brightColorScheme[0]", "#B7C8EA"); // Button Color  #B7C8EA
        brightColorScheme[1] = sharedPref.getString("brightColorScheme[1]", "#3A5A9B"); // Text Color   #3A5A9B
        brightColorScheme[2] = sharedPref.getString("brightColorScheme[2]", "#7CA3F1"); // Background Color   #7CA3F1

        String[] darkColorScheme = new String[3];
        darkColorScheme[0] = sharedPref.getString("darkColorScheme[0]", "#464646"); // Button Color
        darkColorScheme[1] = sharedPref.getString("darkColorScheme[1]", "#C5C5C5"); // Text Color
        darkColorScheme[2] = sharedPref.getString("darkColorScheme[2]", "#555555"); // Background Color

        boolean[] show = new boolean[4];
        show[0] = sharedPref.getBoolean("show[0]", true);
        show[1] = sharedPref.getBoolean("show[1]", true);
        show[2] = sharedPref.getBoolean("show[2]", true);
        show[3] = sharedPref.getBoolean("show[3]", true);

        byte[] BackgroundImageByteArray = loadByteArray("BackgroundImage", context);
        Bitmap BackgroundImageBitmap = null;
        if(BackgroundImageByteArray != null) {
            BackgroundImageBitmap = BitmapFactory.decodeByteArray(BackgroundImageByteArray, 0, BackgroundImageByteArray.length);
        }

        Integer[] darkMode = new Integer[3];
        darkMode[0] = sharedPref.getInt("darkMode[0]", 19);
        darkMode[1] = sharedPref.getInt("darkMode[1]", 7);
        darkMode[2] = sharedPref.getInt("darkMode[2]", 0);

        return new JulyTimersave(startTime, endTime, brightColorScheme, darkColorScheme, show, BackgroundImageBitmap, darkMode);
    }

    public static JulyTimersave reset(Context context) {
        String startTime = "2023-01-01 05:10:00.000";
        String endTime = "2024-01-01 05:10:00.000";

        String[] brightColorScheme = new String[3];
        brightColorScheme[0] = "#B7C8EA";
        brightColorScheme[1] = "#3A5A9B";
        brightColorScheme[2] = "#7CA3F1";

        String[] darkColorScheme = new String[3];
        darkColorScheme[0] = "#464646";
        darkColorScheme[1] = "#C5C5C5";
        darkColorScheme[2] = "#555555";

        boolean[] show = new boolean[4];
        show[0] = true;
        show[1] = true;
        show[2] = true;
        show[3] = true;

        Integer[] darkMode = new Integer[3];
        darkMode[0] = 19;
        darkMode[1] = 7;
        darkMode[2] = 0;

        JulyTimersave save = new JulyTimersave(startTime, endTime, brightColorScheme, darkColorScheme, show, null, darkMode);
        save(save, context);
        return save;
    }

    private static byte[] loadByteArray(String name, Context context) {
        SharedPreferences sharedPref;
        sharedPref = context.getSharedPreferences("JulyTimer", Context.MODE_PRIVATE);
        byte[] result = null;
        String string = sharedPref.getString(name, null);
        if(string.equals("kein BG gesetzt.")) {
            string = null;
        }
        if (string != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                result = Base64.getDecoder().decode(string);
            }
        }
        return result;
    }
}
