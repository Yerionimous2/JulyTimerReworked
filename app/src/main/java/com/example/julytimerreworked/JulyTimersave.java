package com.example.julytimerreworked;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

public class JulyTimersave {
    private String startTime;
    private String endTime;
    private String[] brightColorScheme;
    private String[] darkColorScheme;
    private boolean[] show;
    private Bitmap backgroundImage;
    private Integer[] darkMode;

    public JulyTimersave(String s, String e, String[] bcs, String[] dcs, boolean[] show, Bitmap bi, Integer[] dm) {
        this.startTime = s;
        this.endTime = e;
        this.brightColorScheme = bcs;
        this.darkColorScheme = dcs;
        this.show = show;
        this.backgroundImage = bi;
        this.darkMode = dm;
    }

    @NonNull
    public String toString() {
        String result = "";
        result+= "StartTime = " + startTime + "\n";
        result+= "EndTime   = " + endTime + "\n";
        if(show[0]) {
            result+= "Sekunden werden angezeigt \n";
        }
        if(show[1]) {
            result+= "Minuten werden angezeigt \n";
        }
        if(show[2]) {
            result+= "Stunden werden angezeigt \n";
        }
        if(show[3]) {
            result+= "Tage werden angezeigt \n";
        }
        if(backgroundImage == null) {
            result+= "Das Hintergrundbild ist nicht gesetzt\n";
        } else result+= "Das Hintergrundbild ist gesetzt\n";
        return result;
    }

    public Integer[] getDarkMode(){
        return this.darkMode;
    }
    public void setDarkMode(Integer[] darkMode) {this.darkMode = darkMode;}

    // Getter and Setter for startTime
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    // Getter and Setter for endTime
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    // Getter and Setter for brightColorScheme
    public String[] getBrightColorScheme() {
        return brightColorScheme;
    }

    public void setBrightColorScheme(String[] brightColorScheme) {
        this.brightColorScheme = brightColorScheme;
    }

    // Getter and Setter for darkColorScheme
    public String[] getDarkColorScheme() {
        return darkColorScheme;
    }

    public void setDarkColorScheme(String[] darkColorScheme) {
        this.darkColorScheme = darkColorScheme;
    }

    // Getter and Setter for show
    public boolean[] getShow() {
        return show;
    }

    public void setShow(boolean[] show) {
        this.show = show;
    }

    // Getter and Setter for backgroundImage
    public Bitmap getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Bitmap backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}
