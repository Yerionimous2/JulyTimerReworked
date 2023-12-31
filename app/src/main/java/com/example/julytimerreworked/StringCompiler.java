package com.example.julytimerreworked;

import android.content.Context;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Die Klasse `StringCompiler` enthält Hilfsmethoden zum Zusammenstellen von Zeichenketten für Anzeigezwecke.
 * Sie bietet Funktionen für die Formatierung von Zeitspannen, Prozentangaben und Datumszeichenketten.
 */
public class StringCompiler {

    /**
     * Erstellt eine Zeichenkette, die die formatierte Zeitspanne basierend auf den aktivierten Zeiteinheiten anzeigt.
     *
     * @param show    Ein Array, das angibt, welche Zeiteinheiten aktiviert sind.
     * @param time    Die zu formatierende Zeitspanne.
     * @param context Der Anwendungskontext für Ressourcenzugriffe.
     * @return Eine formatierte Zeichenkette der Zeitspanne.
     */
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

    /**
     * Erstellt eine Zeichenkette, die den Prozentwert mit bestimmter Dezimalgenauigkeit darstellt.
     *
     * @param percent Der Prozentwert.
     * @return Eine formatierte Zeichenkette des Prozentwerts.
     */
    public static String getPercentString(double percent) {
        DecimalFormat dform = new DecimalFormat("#.#######");
        StringBuilder result = new StringBuilder(dform.format(percent) + "");
        if(Math.floor(percent) == percent) {
            result.append(".");
        }
        while(result.length() < 10) result.append("0");
        return result.toString();
    }

    /**
     * Erstellt eine Zeichenkette, die den Typ der vergangenen Zeit basierend auf aktivierten Zeiteinheiten angibt.
     *
     * @param show    Ein Array, das angibt, welche Zeiteinheiten aktiviert sind.
     * @param context Der Anwendungskontext für Ressourcenzugriffe.
     * @return Eine formatierte Zeichenkette für den vergangenen Zeittyp.
     */
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

    /**
     * Erstellt eine lesbar formatierte Datumszeichenkette aus einem Datumsstring.
     *
     * @param date Der Datumsstring.
     * @return Eine lesbar formatierte Datumszeichenkette.
     */
    public static String getReadableDateString(String date) {
        int[] dateInt = parseDateString(date);
        return getReadableDateString(dateInt);
    }

    /**
     * Erstellt eine lesbar formatierte Datumszeichenkette aus einem Datumsstring.
     *
     * @param dateInt Das Datumsarray.
     * @return Eine lesbar formatierte Datumszeichenkette.
     */
    public static String getReadableDateString(int[] dateInt) {
        String result = dateInt[2] + " ";
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

        if(dateInt[3] < 10) result += "0";
        result += dateInt[3] + ":";
        if(dateInt[4] < 10) result += "0";
        result += dateInt[4];
        return result;
    }

    /**
     * Parst einen Datumsstring und gibt ein Integer-Array mit den Einzelteilen des Datums zurück.
     *
     * @param dateString Der Datumsstring.
     * @return Ein Integer-Array mit Jahr, Monat, Tag, Stunde und Minute.
     */
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

    /**
     * Erstellt eine Zeichenkette, die die verbleibende Zeit bis zum Anzeigen basierend auf den ausgewählten Zeitkomponenten darstellt.
     *
     * @param show    Ein boolean-Array, das angibt, welche Zeitkomponenten angezeigt werden sollen.
     * @param context Der Kontext der Anwendung.
     * @return Eine Zeichenkette, die die verbleibende Zeit bis zum Anzeigen repräsentiert.
     */
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

    /**
     * Erstellt eine Datumszeichenkette im Muster "yyyy-MM-dd HH:mm:ss.SSS" aus einem Integer-Array.
     *
     * @param dateArray Das Integer-Array mit Jahr, Monat, Tag, Stunde und Minute.
     * @return Eine Datumszeichenkette im Muster "yyyy-MM-dd HH:mm:ss.SSS".
     */
    public static String getPatternDate(int[] dateArray) {
        StringBuilder result = new StringBuilder();                          //"yyyy-MM-dd HH:mm:ss.SSS"
        String result0;

        result0 = Integer.toString(dateArray[0]);
        while(result0.length()+result.length() < 4) {
            result.append("0");
        }
        result.append(result0).append("-");

        result0 = Integer.toString(dateArray[1]);
        while(result0.length()+result.length() < 7) {
            result.append("0");
        }
        result.append(result0).append("-");

        result0 = Integer.toString(dateArray[2]);
        while(result0.length()+result.length() < 10) {
            result.append("0");
        }
        result.append(result0).append(" ");

        result0 = Integer.toString(dateArray[3]);
        while(result0.length()+result.length() < 13) {
            result.append("0");
        }
        result.append(result0).append(":");

        result0 = Integer.toString(dateArray[4]);
        while(result0.length()+result.length() < 16) {
            result.append("0");
        }
        result.append(result0).append(":00.000");
        return result.toString();
    }


    /**
     * Wandelt ein LocalDateTime-Objekt in eine Zeichenkette im Muster "yyyy-MM-dd HH:mm:ss.SSS" um.
     *
     * @param now Das LocalDateTime-Objekt.
     * @return Eine Zeichenkette im Muster "yyyy-MM-dd HH:mm:ss.SSS".
     */
    public static String getStringFromDateTime(LocalDateTime now) {
        // Gewünschtes Format
        DateTimeFormatter formatter;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            return(now.format(formatter));
        }
        // LocalDateTime in String umwandeln
        return "";
    }

    /**
     * Diese Methode generiert einen angepassten Text für den Dark Mode-Button, basierend auf den gespeicherten Einstellungen
     * und dem Anwendungskontext.
     *
     * @param save Die {@code JulyTimersave}-Instanz, die die gespeicherten Einstellungen enthält.
     * @param context Der Anwendungskontext, der für die Ressourcenauflösung benötigt wird.
     * @return Ein {@code String}, der den angepassten Text für den Dark Mode-Button darstellt.
     */
    public static String getCustomizeDarkModeButtonText(JulyTimersave save, Context context) {
        String result;
        result = context.getString(R.string.colorScheme);
        result += " ";
        if(save.getDarkMode()[2] == 1) {
            result += context.getString(R.string.bright);
        }
        if(save.getDarkMode()[2] == 2) {
            result += context.getString(R.string.dark);
        }
        if(save.getDarkMode()[2] == 0) {
            result += context.getString(R.string.automatic);
        }
        return result;
    }

    /**
     * Diese Methode generiert einen Text, der den Startzeitpunkt des Dark Mode darstellt,
     * basierend auf den gespeicherten Einstellungen und dem Anwendungskontext.
     *
     * @param save Die {@code JulyTimersave}-Instanz, die die gespeicherten Einstellungen enthält.
     * @param context Der Anwendungskontext, der für die Ressourcenauflösung benötigt wird.
     * @return Ein {@code String}, der den Text für den Startzeitpunkt des Dark Mode darstellt.
     */
    public static String getStartDarkModeText(JulyTimersave save, Context context) {
        String result = context.getString(R.string.beginDarkMode);
        result += " " + save.getDarkMode()[0] + " " + context.getString(R.string.oClock);
        return result;
    }

    /**
     * Diese Methode generiert einen Text, der den Endzeitpunkt des Dark Mode darstellt,
     * basierend auf den gespeicherten Einstellungen und dem Anwendungskontext.
     *
     * @param save Die {@code JulyTimersave}-Instanz, die die gespeicherten Einstellungen enthält.
     * @param context Der Anwendungskontext, der für die Ressourcenauflösung benötigt wird.
     * @return Ein {@code String}, der den Text für den Endzeitpunkt des Dark Mode darstellt.
     */
    public static String getEndDarkModeText(JulyTimersave save, Context context) {
        String result = context.getString(R.string.endDarkMode);
        result += " " + save.getDarkMode()[1] + " " + context.getString(R.string.oClock);
        return result;
    }

    /**
     * Diese Methode generiert einen Text, der die Haupteinstellung für die Anzeige von Nachrichten darstellt,
     * basierend auf den gespeicherten Einstellungen und dem Anwendungskontext.
     *
     * @param save Die {@code JulyTimersave}-Instanz, die die gespeicherten Einstellungen enthält.
     * @param context Der Anwendungskontext, der für die Ressourcenauflösung benötigt wird.
     * @return Ein {@code String}, der den Text für die Haupteinstellung zur Anzeige von Nachrichten darstellt.
     */
    public static String getMainSettingsMessageText(JulyTimersave save, Context context) {
        String result = context.getString(R.string.showMessage);
        if(save.isGetMessage()) {
            result += " " + context.getString(R.string.yes);
        } else {
            result += " " + context.getString(R.string.no);
        }
        return result;
    }

    /**
     * Diese Methode ersetzt das letzte Zeichen in einem gegebenen String durch ein neues Zeichen.
     *
     * @param content Der ursprüngliche {@code String}, dessen letztes Zeichen ersetzt werden soll.
     * @param replaceWith Das Zeichen, durch das das letzte Zeichen ersetzt werden soll.
     * @return Ein neuer {@code String}, der das ursprüngliche String mit dem ersetzen letzten Zeichen darstellt.
     */
    public static String replacelast(String content, char replaceWith) {
        content = content.substring(0, content.length() - 1);
        content += replaceWith;
        return content;
    }

    /**
     * Diese Methode ersetzt einen Buchstaben in einem String mit einem anderen.
     *
     * @param content der ursprüngliche String.
     * @param indexToReplace der Index des Buchstabens im String, der ersetzt werden soll.
     * @param replaceWith der Buchstabe, durch den ersetzt werden soll.
     * @return ein String, bei dem der Buchstabe ersetzt wurde.
     */
    public static String replace(String content, int indexToReplace, char replaceWith) {
        // Den ursprünglichen String in einen Char-Array konvertieren
        char[] charArray = content.toCharArray();

        // Den Buchstaben an der angegebenen Position ersetzen
        charArray[indexToReplace] = replaceWith;

        // Den modifizierten Char-Array zurück in einen String konvertieren
        return new String(charArray);
    }

    public static String getTillGoString(boolean[] show, Context context) {
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
        result += context.getString(R.string.time_till_go);
        return result;
    }
}
