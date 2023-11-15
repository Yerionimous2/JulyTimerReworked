package com.example.julytimerreworked;

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
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.show_customize, container, false);
        ActionBar actionBar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        JulyTimersave save = saveExec.load(view.getContext());

        Button btBack = view.findViewById(R.id.customizeTimeShowBack);
        btBack.setOnClickListener((View) -> {
            requireActivity().onBackPressed();
        });

        CheckBox cbSeconds = view.findViewById(R.id.cbSeconds);
        CheckBox cbMinutes = view.findViewById(R.id.cbMinutes);
        CheckBox cbHours = view.findViewById(R.id.cbHours);
        CheckBox cbDays = view.findViewById(R.id.cbDays);
        boolean[] show = save.getShow();
        cbSeconds.setChecked(show[0]);
        cbMinutes.setChecked(show[1]);
        cbHours.setChecked(show[2]);
        cbDays.setChecked(show[3]);

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }


        return view;
    }
}
