package com.example.julytimerreworked;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Die MainActivity repräsentiert die Hauptaktivität der Anwendung, die den Timer anzeigt.
 */
public class MainActivity extends AppCompatActivity {
    private JulyTimersave save;

    /**
     * Wird aufgerufen, wenn die Aktivität erstellt wird.
     *
     * @param savedInstanceState Ein Bundle-Objekt, das den Zustand der Aktivität enthält.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = this;
        save = saveExec.load(context);
        initialiseTextViewTexts();
        updateTextViewTexts();
        changeColors();
        initialiseTimer();
        initialiseButtonListener();
    }

    //TODO: doku
    private void initialiseButtonListener() {
        Button btSettings = findViewById(R.id.BtSettings);
        Context context = this;
        btSettings.setOnClickListener((View view) -> {
            saveExec.save(save, this);
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });
    }

    /**
     * Wird aufgerufen, wenn die Aktivität in den Vordergrund kommt oder wiederhergestellt wird.
     */
    protected void onResume() {
        super.onResume();
        save = saveExec.load(this);
        initialiseTextViewTexts();
        updateTextViewTexts();
        changeColors();
    }

    /**
     * Initialisiert den Timer, der die TextViews periodisch aktualisiert.
     */
    public void initialiseTimer() {
        Handler handler = new Handler();
        int milliDelay = 50;
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                updateTextViewTexts();
                // Aktivität erneut starten
                handler.postDelayed(this, milliDelay);
            }
        };
        handler.postDelayed(myRunnable, milliDelay);
    }

    /**
     * Ändert die Farbschemata der Benutzeroberfläche basierend auf den gespeicherten Daten.
     * Wählt das richtige Farbchema und sendet es an setColors, welche die Farbänderung durchführt.
     */
    public void changeColors() {
        if(save.getDarkMode()[2] == 0) {
            if(timeExec.checkTime(save.getDarkMode())) {
                setColors(save.getBrightColorScheme());
            } else {
                setColors(save.getDarkColorScheme());
            }
        }
        if(save.getDarkMode()[2] == 1) {
            setColors(save.getBrightColorScheme());
        }
        if(save.getDarkMode()[2] == 2) {
            setColors(save.getDarkColorScheme());
        }
    }

    /**
     * Setzt die Farben der UI-Elemente basierend auf dem übergebenen Farbschema.
     *
     * @param colorScheme Ein String-Array, das die Farben für die Schaltflächen, den Text und den Hintergrund enthält.
     */
    public void setColors(String[] colorScheme) {
        int buttonColor = Color.parseColor(colorScheme[0]);
        int textColor = Color.parseColor(colorScheme[1]);
        int backgroundColor = Color.parseColor(colorScheme[2]);

        //Labels
        TextView lb1 = findViewById(R.id.lb1);
        TextView lb2 = findViewById(R.id.lb2);
        TextView lb3 = findViewById(R.id.lb3);
        TextView lb4 = findViewById(R.id.lb4);
        TextView lb5 = findViewById(R.id.lb5);
        TextView lb6 = findViewById(R.id.lb6);
        lb1.setTextColor(textColor);
        lb2.setTextColor(textColor);
        lb3.setTextColor(textColor);
        lb4.setTextColor(textColor);
        lb5.setTextColor(textColor);
        lb6.setTextColor(textColor);

        Button btSettings = findViewById(R.id.BtSettings);
        btSettings.setTextColor(textColor);

        //Mini-Hintergründe
        LinearLayout layout1 = findViewById(R.id.layout1);
        LinearLayout layout2 = findViewById(R.id.layout2);
        LinearLayout layout3 = findViewById(R.id.layout3);
        layout1.setBackgroundColor(buttonColor);
        layout2.setBackgroundColor(buttonColor);
        layout3.setBackgroundColor(buttonColor);

        //Hintergrund
        ImageView background = findViewById(R.id.imBackground);
        background.setImageBitmap(save.getBackgroundImage());
        background.setAlpha((float) 0.6);

        View layout = findViewById(R.id.mainLayout);
        layout.setBackgroundColor(backgroundColor);
    }

    /**
     * Initialisiert die TextView-Texte basierend auf den gespeicherten Daten.
     */
    public void initialiseTextViewTexts() {
        TextView label1 = findViewById(R.id.lb1);
        TextView label3 = findViewById(R.id.lb3);
        label1.setText(StringCompiler.getElapsedString(save.getShow(), this));
        label3.setText(StringCompiler.getTillSeenString(save.getShow(), this));
    }

    /**
     * Aktualisiert die TextView-Texte basierend auf den Timer-Zeitdaten.
     */
    public void updateTextViewTexts() {
        TextView label2 = findViewById(R.id.lb2);
        TextView label4 = findViewById(R.id.lb4);
        TextView label6 = findViewById(R.id.lb6);
        xyzSet times = new xyzSet(save);
        label2.setText(StringCompiler.getTimeString(save.getShow(), times.getElapsed(), this));
        label4.setText(StringCompiler.getTimeString(save.getShow(), times.getRemaining(), this));
        label6.setText(StringCompiler.getPercentString(times.getPercent()));
    }
}