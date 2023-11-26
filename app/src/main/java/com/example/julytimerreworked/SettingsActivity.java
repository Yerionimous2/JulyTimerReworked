package com.example.julytimerreworked;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.julytimerreworked.databinding.ActivitySettingsBinding;

/**
 * Die Klasse `SettingsActivity` repräsentiert die Aktivität zur Verwaltung der Anwendungseinstellungen.
 * Sie ermöglicht Benutzern die Konfiguration verschiedener Optionen im Zusammenhang mit Zeitintervallen und anderen Präferenzen.
 *
 * Diese Aktivität umfasst Methoden zum Aktualisieren und Speichern von Benutzereinstellungen, zum Umgang mit Navigation
 * sowie zum Initialisieren der Benutzeroberflächenelemente.
 */
public class SettingsActivity extends AppCompatActivity {
    private JulyTimersave save;

    private AppBarConfiguration appBarConfiguration;

    /**
     * Wird aufgerufen, wenn die Aktivität erstellt wird. Initialisiert das Layout der Aktivität, die Navigation und die Einstellungen.
     *
     * @param savedInstanceState Ein Bundle, das den zuvor gespeicherten Zustand der Aktivität enthält, falls vorhanden.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.julytimerreworked.databinding.ActivitySettingsBinding binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        save = saveExec.load(this);

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_settings);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    /**
     * Behandelt den Klick auf die "Nach oben" Navigations-Schaltfläche.
     *
     * @return True, wenn die Navigation behandelt wurde, andernfalls false.
     */
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_settings);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**
     * Überschreibt den Druck auf die Zurück-Schaltfläche, um Benutzereinstellungen zu aktualisieren und zu speichern, bevor zurücknavigiert wird.
     */
    @Override
    public void onBackPressed() {
        updateSave();
        saveExec.save(save, this);
        super.onBackPressed();
    }

    /**
     * Aktualisiert die Benutzereinstellungen basierend auf dem Zustand der Kontrollkästchen in der Zeitkonfiguration und speichert die Änderungen.
     */
    private void updateSave() {
        boolean[] show;
        save = saveExec.load(this);
        show = save.getShow();

        //Update the time show intervals
        CheckBox cbSeconds = findViewById(R.id.cbSeconds);
        CheckBox cbMinutes = findViewById(R.id.cbMinutes);
        CheckBox cbHours = findViewById(R.id.cbHours);
        CheckBox cbDays = findViewById(R.id.cbDays);
        boolean isSecondsChecked = show[0];
        boolean isMinutesChecked = show[1];
        boolean isHoursChecked = show[2];
        boolean isDaysChecked = show[3];
        if(cbSeconds != null) isSecondsChecked = cbSeconds.isChecked();
        if(cbMinutes != null) isMinutesChecked = cbMinutes.isChecked();
        if(cbHours != null) isHoursChecked = cbHours.isChecked();
        if(cbDays != null) isDaysChecked = cbDays.isChecked();
        show[0] = isSecondsChecked;
        show[1] = isMinutesChecked;
        show[2] = isHoursChecked;
        show[3] = isDaysChecked;

        save.setShow(show);
        saveExec.save(save, this);
    }
}