package com.example.julytimerreworked;

import android.content.Context;

import java.text.DecimalFormat;

public class StringCompiler {
    public static String getTimeString(boolean[] show, long time, Context context) {
        String result = "";
        int enabled = 0;
        for(boolean shows:show) {
            if(shows) {
                enabled += 1;
            }
        }
        if(enabled == 0) {
            show[0] = true;
            show[1] = true;
            show[2] = true;
            show[3] = true;
            enabled = 4;
        }
        if(enabled > 1) {
            if ((time >= 86400) && (show[3])) {
                result += (int) time / 86400;
                result += "d ";
                time = time % 86400;
            }
            if ((time >= 3600) && (show[2])) {
                result += (int) time / 3600;
                result += "h ";
                time = time % 3600;
            }
            if ((time >= 60) && (show[1])) {
                result += (int) time / 60;
                result += "min ";
                time = time % 60;
            }
            if ((time > 0) && (show[0])) {
                result += time;
                result += "s";
            }
        }
        if(enabled == 1) {
            if(show[0]) {
                result += (int) time;
                result += " " + context.getString(R.string.seconds);
            }
            if(show[1]) {
                result += (int) time / 60;
                result += " " + context.getString(R.string.minutes);
            }
            if(show[2]) {
                result += (int) time / 3600;
                result += " " + context.getString(R.string.hours);
            }
            if(show[3]) {
                result += (int) time / 86400;
                result += " " + context.getString(R.string.days);
            }
        }
        return result;
    }

    public static String getPercentString(double percent) {
        DecimalFormat dform = new DecimalFormat("#.#######");
        String result = dform.format(percent) + "";
        if(Math.floor(percent) == percent) {
            result += ".";
        }
        while(result.length() < 10) result += "0";
        return result;
    }

    public static String getElapsedString(boolean[] show, Context context) {
        int enabled = 0;
        for(boolean shows:show) {
            if(shows) {
                enabled += 1;
            }
        }
        String result = "";
        if(enabled >= 1) {
            result += context.getString(R.string.time);
        } else {
            if(show[0]) {
                result += "" + context.getString(R.string.days);
            }
            if(show[1]) {
                result += "" + context.getString(R.string.hours);
            }
            if(show[2]) {
                result += "" + context.getString(R.string.minutes);
            }
            if(show[3]) {
                result += "" + context.getString(R.string.seconds);
            }
        }
        result += context.getString(R.string.time_elapsed);
        return result;
    }

    public static String getReadableDateString(String date) {
        String result = "";
        int[] dateInt = parseDateString(date);
        result += dateInt[2] + " ";
        switch (dateInt[1]) {
            case 1:
                result += "JAN";
                break;
            case 2:
                result += "FEB";
                break;
            case 3:
                result += "MAR";
                break;
            case 4:
                result += "APR";
                break;
            case 5:
                result += "MAY";
                break;
            case 6:
                result += "JUN";
                break;
            case 7:
                result += "JUL";
                break;
            case 8:
                result += "AUG";
                break;
            case 9:
                result += "SEP";
                break;
            case 10:
                result += "OCT";
                break;
            case 11:
                result += "NOV";
                break;
            case 12:
                result += "DEC";
                break;
            default:
                result = "";
                break;
        }
        result += " " + dateInt[0] + " ";

        result += dateInt[3] + ":" + dateInt[4];
        return result;
    }

    public static int[] parseDateString(String dateString) {
        int[] result = new int[5];

        // Extrahiere die Zahlen aus dem String
        String[] parts = dateString.split("\\D");

        // Setze die Werte im Ergebnisarray
        for (int i = 0; i < 5; i++) {
            result[i] = Integer.parseInt(parts[i]);
        }

        return result;
    }

    public static String getTillSeenString(boolean[] show, Context context) {
        int enabled = 0;
        for(boolean shows:show) {
            if(shows) {
                enabled += 1;
            }
        }
        String result = "";
        if(enabled >= 1) {
            result += context.getString(R.string.time);
        } else {
            if(show[0]) {
                result += "" + context.getString(R.string.days);
            }
            if(show[1]) {
                result += "" + context.getString(R.string.hours);
            }
            if(show[2]) {
                result += "" + context.getString(R.string.minutes);
            }
            if(show[3]) {
                result += "" + context.getString(R.string.seconds);
            }
        }
        result += context.getString(R.string.time_till_seen);
        return result;
    }
}
