package com.example.julytimerreworked;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class changeDates extends Fragment {
    View view;
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        view = inflater.inflate(R.layout.dates_change, container, false);
        JulyTimersave save = saveExec.load(view.getContext());
        removeBackArrow();
        setBackgroundImage(save);
        setButtonTexts(save);
        setButtonListeners(save);
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

        Button btBack = view.findViewById(R.id.changeDatesBackButton);
        Button btPickStartDate = view.findViewById(R.id.changeDatesStartDateButton);
        Button btPickEndDate = view.findViewById(R.id.changeDatesEndDateButton);

        TextView lbStartDate = view.findViewById(R.id.changeDatesStartDateText);
        TextView lbEndDate = view.findViewById(R.id.changeDatesEndDateText);

        View layout = view.findViewById(R.id.changeDatesLayout);

        btBack.setBackgroundColor(buttonColor);
        btPickStartDate.setBackgroundColor(buttonColor);
        btPickEndDate.setBackgroundColor(buttonColor);
        lbStartDate.setBackgroundColor(buttonColor);
        lbEndDate.setBackgroundColor(buttonColor);

        btBack.setTextColor(textColor);
        btPickStartDate.setTextColor(textColor);
        btPickEndDate.setTextColor(textColor);
        lbStartDate.setTextColor(textColor);
        lbEndDate.setTextColor(textColor);

        layout.setBackgroundColor(backgroundColor);
    }

    private void setButtonListeners(JulyTimersave save) {
        Button btPickStartDate = view.findViewById(R.id.changeDatesStartDateButton);
        Button btPickEndDate = view.findViewById(R.id.changeDatesEndDateButton);
        Button btBack = view.findViewById(R.id.changeDatesBackButton);

        //TODO: Implement changing Dates
        btBack.setOnClickListener((View) -> requireActivity().onBackPressed());
    }

    private void setButtonTexts(JulyTimersave save) {
        Button btPickStartDate = view.findViewById(R.id.changeDatesStartDateButton);
        Button btPickEndDate = view.findViewById(R.id.changeDatesEndDateButton);

        btPickStartDate.setText(StringCompiler.getReadableDateString(save.getStartTime()));
        btPickEndDate.setText(StringCompiler.getReadableDateString(save.getEndTime()));
    }

    private void removeBackArrow() {
        ActionBar actionBar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    private void setBackgroundImage(JulyTimersave save) {
        ImageView background = view.findViewById(R.id.mainSettingsBackground);
        if(background != null) background.setAlpha((float) 0.6);
        if(save.getBackgroundImage() != null) background.setImageBitmap(save.getBackgroundImage());
    }

}
