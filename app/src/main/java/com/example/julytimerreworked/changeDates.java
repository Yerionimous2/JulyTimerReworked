package com.example.julytimerreworked;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class changeDates extends Fragment {
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        View view = inflater.inflate(R.layout.dates_change, container, false);
        ActionBar actionBar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        JulyTimersave save = saveExec.load(view.getContext());

        Button btPickStartDate = view.findViewById(R.id.changeDatesStartDateButton);
        btPickStartDate.setText(StringCompiler.getReadableDateString(save.getStartTime()));
        //TODO: Implement changing Dates?

        Button btPickEndDate = view.findViewById(R.id.changeDatesEndDateButton);
        btPickEndDate.setText(StringCompiler.getReadableDateString(save.getEndTime()));
        //TODO: Implement changing Dates?

        Button btBack = view.findViewById(R.id.changeDatesBackButton);
        btBack.setOnClickListener((View) -> {
            requireActivity().onBackPressed();
        });

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }


        return view;
    }

}
