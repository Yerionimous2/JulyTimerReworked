package com.example.julytimerreworked;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Die Klasse `xyzSet` ist eine Momentaufnahme, wie weit die Zeit vorangeschritten ist.
 */
public class xyzSet {
    private long elapsed;
    private long remaining;
    private double percent;

    public long getElapsed() {
        return elapsed;
    }

    public long getRemaining() {
        return remaining;
    }

    public double getPercent() {
        return percent;
    }

    /**
     * Konstruktor für die Klasse `xyzSet`. Berechnet verstrichene Zeit, verbleibende Zeit und Prozentsatz.
     *
     * @param times Ein `JulyTimersave`-Objekt mit den Start- und Endzeiten.
     */
    public xyzSet(JulyTimersave times) {
        DateTimeFormatter dr = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dr = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        }
        LocalDateTime now;
        String nowString = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDateTime.now(ZoneId.of("Europe/Berlin"));
            nowString = now.format(dr);
        }
        long completeTime = timeExec.getDifferenceMilliSeconds(times.getStartTime(), times.getEndTime());
        this.elapsed = timeExec.getDifferenceMilliSeconds(times.getStartTime(), nowString)/1000;
        this.remaining = timeExec.getDifferenceMilliSeconds(nowString, times.getEndTime())/1000;
        this.percent = (double)timeExec.getDifferenceMilliSeconds(times.getStartTime(), nowString)/(double)completeTime*100;
        if(this.percent > 100) {
            this.elapsed = completeTime;
            this.remaining = 0;
            this.percent = 100;
        }
    }
}
