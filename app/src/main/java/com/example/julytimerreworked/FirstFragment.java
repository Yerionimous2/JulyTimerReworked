package com.example.julytimerreworked;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

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
        btCustomizeShowTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_FirstFragment_to_customizeShow);
            }
        });

        Button btChangeDates = view.findViewById(R.id.mainSettingsChangeDates);
        btChangeDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_FirstFragment_to_changeDates);
            }
        });

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