package com.example.julytimerreworked;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.julytimerreworked.databinding.FragmentFirstBinding;


public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // Aufgeblasene View holen
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        // Button suchen und behandeln
        Button btCustomizeShowTime = view.findViewById(R.id.mainSettingsTimeShow);
        btCustomizeShowTime.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_FirstFragment_to_customizeShow));

        Button btChangeDates = view.findViewById(R.id.mainSettingsChangeDates);
        btChangeDates.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_FirstFragment_to_changeDates));

        ImageView background = view.findViewById(R.id.mainSettingsBackground);
        background.setBackgroundColor(Color.RED);
        background.invalidate();

        // Jetzt die binding-Variable initialisieren
        //binding = FragmentFirstBinding.inflate(inflater, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}