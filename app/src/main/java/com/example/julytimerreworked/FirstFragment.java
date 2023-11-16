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



public class FirstFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        view = inflater.inflate(R.layout.fragment_first, container, false);

        JulyTimersave save = saveExec.load(view.getContext());

        setButtonListeners();
        setBackgroundImage(save);
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

        Button btBack = view.findViewById(R.id.mainSettingsBack);
        Button btCustomizeShowTime = view.findViewById(R.id.mainSettingsTimeShow);
        Button btChangeDates = view.findViewById(R.id.mainSettingsChangeDates);
        Button btCustomizeDarkMode = view.findViewById(R.id.mainSettingsCustomizeDarkMode);
        Button btChangeBackground = view.findViewById(R.id.mainSettingsChangeBackground);
        Button btShowMileStones = view.findViewById(R.id.mainSettingsShowMilestones);
        Button btReset = view.findViewById(R.id.mainSettingsReset);

        View layout = view.findViewById(R.id.settingsMainLayout);

        btBack.setBackgroundColor(buttonColor);
        btCustomizeShowTime.setBackgroundColor(buttonColor);
        btChangeDates.setBackgroundColor(buttonColor);
        btCustomizeDarkMode.setBackgroundColor(buttonColor);
        btChangeBackground.setBackgroundColor(buttonColor);
        btShowMileStones.setBackgroundColor(buttonColor);
        btReset.setBackgroundColor(buttonColor);

        btBack.setTextColor(textColor);
        btCustomizeShowTime.setTextColor(textColor);
        btChangeDates.setTextColor(textColor);
        btCustomizeDarkMode.setTextColor(textColor);
        btChangeBackground.setTextColor(textColor);
        btShowMileStones.setTextColor(textColor);
        btReset.setTextColor(textColor);

        layout.setBackgroundColor(backgroundColor);
    }

    private void setButtonListeners() {
        Button btCustomizeShowTime = view.findViewById(R.id.mainSettingsTimeShow);
        btCustomizeShowTime.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_FirstFragment_to_customizeShow));

        Button btChangeDates = view.findViewById(R.id.mainSettingsChangeDates);
        btChangeDates.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_FirstFragment_to_changeDates));
    }

    private void setBackgroundImage(JulyTimersave save) {
        ImageView background = view.findViewById(R.id.mainSettingsBackground);
        background.setAlpha((float) 0.6);
        if(save.getBackgroundImage() != null) background.setImageBitmap(save.getBackgroundImage());
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}