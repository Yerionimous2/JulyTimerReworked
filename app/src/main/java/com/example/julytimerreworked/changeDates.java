package com.example.julytimerreworked;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
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
    DatePickerDialog startDatePicker;
    TimePickerDialog startTimePicker;
    DatePickerDialog endDatePicker;
    TimePickerDialog endTimePicker;

    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        view = inflater.inflate(R.layout.dates_change, container, false);
        JulyTimersave save = saveExec.load(view.getContext());
        removeBackArrow();
        createDateTimePickers(save);
        setBackgroundImage(save);
        setButtonTexts(save);
        setButtonListeners(save);
        changeColors(save);
        setBackgroundImage(save);
        return view;
    }

    private void createDateTimePickers(JulyTimersave save) {
        Context context = view.getContext();
        Button btPickStartDate = view.findViewById(R.id.changeDatesStartDateButton);
        Button btPickEndDate = view.findViewById(R.id.changeDatesEndDateButton);
        int[] startDateIntArray = StringCompiler.parseDateString(save.getStartTime());
        int[] resultStartDateIntArray = startDateIntArray;
        int[] endDateIntArray = StringCompiler.parseDateString(save.getEndTime());
        int[] resultEndDateIntArray = endDateIntArray;

        startDatePicker = new DatePickerDialog(context,
                (view, year, monthOfYear, dayOfMonth) -> {
                    resultStartDateIntArray[0] = year;
                    resultStartDateIntArray[1] = monthOfYear + 1;
                    resultStartDateIntArray[2] = dayOfMonth;
                }, startDateIntArray[0], startDateIntArray[1] - 1, startDateIntArray[2]);
        startTimePicker = new TimePickerDialog(context,
                (view, hourOfDay, minute) -> {

                    resultStartDateIntArray[3] = hourOfDay;
                    resultStartDateIntArray[4] = minute;
                    if(timeExec.validate(resultStartDateIntArray, endDateIntArray)) {
                        btPickStartDate.setText(StringCompiler.getReadableDateString(resultStartDateIntArray));
                        save.setStartTime(StringCompiler.getPatternDate(resultStartDateIntArray));
                        saveExec.save(save, view.getContext());
                    }
                }, startDateIntArray[3], startDateIntArray[4], true);
        endDatePicker = new DatePickerDialog(context,
                (view, year, monthOfYear, dayOfMonth) -> {
                    resultEndDateIntArray[0] = year;
                    resultEndDateIntArray[1] = monthOfYear + 1;
                    resultEndDateIntArray[2] = dayOfMonth;
                }, endDateIntArray[0], endDateIntArray[1] - 1, endDateIntArray[2]);
        endTimePicker = new TimePickerDialog(context,
                (view, hourOfDay, minute) -> {

                    resultEndDateIntArray[3] = hourOfDay;
                    resultEndDateIntArray[4] = minute;
                    if(timeExec.validate(startDateIntArray, resultEndDateIntArray)) {
                        btPickEndDate.setText(StringCompiler.getReadableDateString(resultEndDateIntArray));
                        save.setEndTime(StringCompiler.getPatternDate(resultEndDateIntArray));
                        saveExec.save(save, view.getContext());
                    }
                }, endDateIntArray[3], endDateIntArray[4], true);
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

        btPickStartDate.setOnClickListener((View) -> {
            startTimePicker.show();
            startDatePicker.show();
        });

        btPickEndDate.setOnClickListener((View) -> {
            endTimePicker.show();
            endDatePicker.show();
        });
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
        ImageView background = view.findViewById(R.id.changeDatesBackground);
        background.setAlpha((float) 0.6);
        background.setImageBitmap(save.getBackgroundImage());
    }

}
