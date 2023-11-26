package com.example.julytimerreworked;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Die Klasse `timeExec` enthält Hilfsmethoden zur Zeitverarbeitung und -überprüfung.
 */
public class timeExec {

    /**
     * Berechnet die Differenz in Millisekunden zwischen zwei Zeitstempeln.
     *
     * @param startTime Der Startzeitstempel.
     * @param endTime   Der Endzeitstempel.
     * @return Die Differenz in Millisekunden.
     */
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

    /**
     * Validiert, ob die Endzeit nach der Startzeit liegt und dass die Endzeit nicht in der Zukunft liegt.
     *
     * @param startDateArray Ein Integer-Array mit den Startzeitdaten.
     * @param endDateArray   Ein Integer-Array mit den Endzeitdaten.
     * @return `true`, wenn die Validierung erfolgreich ist, andernfalls `false`.
     */
    public static boolean validate(int[] startDateArray, int[] endDateArray) {
        boolean result = true;
        String startDate = StringCompiler.getPatternDate(startDateArray);
        String endDate = StringCompiler.getPatternDate(endDateArray);
        String nowString = "";
        if(getDifferenceMilliSeconds(startDate, endDate) <= 0) result = false;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime now = LocalDateTime.now();
            nowString = StringCompiler.getStringFromDateTime(now);
        }
        if(getDifferenceMilliSeconds(nowString, endDate) <= 0) result = false;
        return result;
    }

    /**
     * Überprüft, ob ein bestimmter Prozentsatz erreicht ist.
     *
     * @param percent Der aktuelle Prozentwert.
     * @param needed  Der benötigte Prozentwert.
     * @return `true`, wenn der aktuelle Prozentwert den benötigten Prozentwert erreicht oder überschreitet, andernfalls `false`.
     */
    public static boolean done(double percent, double needed) {
        return percent >= needed;
    }

    /**
     * Wandelt einen Datumsstring in einen Zeitstempel um.
     *
     * @param arg Der Datumsstring.
     * @return Der entsprechende Zeitstempel.
     * @throws ParseException Wenn ein Fehler bei der Parsenoperation auftritt.
     */
    public static long timestamp(String arg) throws ParseException {
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return Objects.requireNonNull(df.parse(arg)).getTime();
    }


    /**
     * Überprüft, ob die aktuelle Uhrzeit zwischen den beiden Stundenwerten liegt.
     *
     * @param times Ein Integer-Array mit den Stundenwerten [0] und [1].
     * @return `true`, wenn die aktuelle Uhrzeit zwischen den beiden Werten liegt, andernfalls `false`.
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
