package com.example.julytimerreworked;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.julytimerreworked.databinding.ShowCustomizeBinding;

import java.util.Objects;


public class customizeShow extends Fragment {
    ShowCustomizeBinding binding;
    View view;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        view = inflater.inflate(R.layout.show_customize, container, false);
        removeBackArrow();
        JulyTimersave save = saveExec.load(view.getContext());

        setButtonListeners();
        initialiseCheckboxes(save);
        changeColors(save);

        return view;
    }

    private void changeColors(JulyTimersave save) {
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

    private void setColors(String[] ColorScheme) {
        int buttonColor = Color.parseColor(ColorScheme[0]);
        int textColor = Color.parseColor(ColorScheme[1]);
        int backgroundColor = Color.parseColor(ColorScheme[2]);

        Button btBack = view.findViewById(R.id.customizeTimeShowBack);

        CheckBox cbSeconds = view.findViewById(R.id.cbSeconds);
        CheckBox cbMinutes = view.findViewById(R.id.cbMinutes);
        CheckBox cbHours = view.findViewById(R.id.cbHours);
        CheckBox cbDays = view.findViewById(R.id.cbDays);

        View layout = view.findViewById(R.id.customizeTimeLayout);

        btBack.setBackgroundColor(buttonColor);
        cbSeconds.setBackgroundColor(buttonColor);
        cbMinutes.setBackgroundColor(buttonColor);
        cbHours.setBackgroundColor(buttonColor);
        cbDays.setBackgroundColor(buttonColor);

        btBack.setTextColor(textColor);
        cbSeconds.setTextColor(textColor);
        cbMinutes.setTextColor(textColor);
        cbHours.setTextColor(textColor);
        cbDays.setTextColor(textColor);

        layout.setBackgroundColor(backgroundColor);
    }

    private void initialiseCheckboxes(JulyTimersave save) {
        CheckBox cbSeconds = view.findViewById(R.id.cbSeconds);
        CheckBox cbMinutes = view.findViewById(R.id.cbMinutes);
        CheckBox cbHours = view.findViewById(R.id.cbHours);
        CheckBox cbDays = view.findViewById(R.id.cbDays);
        boolean[] show = save.getShow();
        cbSeconds.setChecked(show[0]);
        cbMinutes.setChecked(show[1]);
        cbHours.setChecked(show[2]);
        cbDays.setChecked(show[3]);
    }

    private void setButtonListeners() {
        Button btBack = view.findViewById(R.id.customizeTimeShowBack);
        btBack.setOnClickListener((View) -> {
            requireActivity().onBackPressed();
        });
    }

    private void removeBackArrow() {
        ActionBar actionBar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }
}
