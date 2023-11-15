package com.example.julytimerreworked;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Objects;

public class timeExec {
    public static long getDifferenceMilliSeconds(String startTime, String endTime) {
        long a = 0, b = 0;
        try {
            a = timestamp(startTime);
            b = timestamp(endTime);
        } catch(ParseException oi) {
            System.out.println("Debug: ParseException in timestamp");
        }
        return(b - a);
    }

    public static long timestamp(String arg) throws ParseException {
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return Objects.requireNonNull(df.parse(arg)).getTime();
    }


    /*
     * Checks, wheather the time is between the [0] and [1] time of the input in hours.
     * true:  it is between the values
     * false: it is not between the values
     */
    public static boolean checkTime(Integer[] times) {
        int stunde = 0;
        // Stunde als Integer auslesen
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalTime aktuelleZeit = LocalTime.now();
            stunde = aktuelleZeit.getHour();
        }
        return (stunde <= times[0]) && (stunde >= times[1]);
    }
}
