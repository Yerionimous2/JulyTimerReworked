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

public class SettingsActivity extends AppCompatActivity {
    private JulyTimersave save;
    private customizeShow customizeFragment;

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.julytimerreworked.databinding.ActivitySettingsBinding binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        save = saveExec.load(this);

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_settings);
        customizeFragment = (customizeShow) getSupportFragmentManager().findFragmentById(R.id.customizeShow);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        Button btBack = findViewById(R.id.mainSettingsBack);
        btBack.setOnClickListener((View) -> {
            onBackPressed();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_settings);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        updateSave();
        saveExec.save(save, this);
        super.onBackPressed();
    }


    private void updateSave() {
        boolean[] show;
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
    }
}