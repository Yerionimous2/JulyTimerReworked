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

import yuku.ambilwarna.AmbilWarnaDialog;

public class customizeColorScheme extends Fragment {

    View view;
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        view = inflater.inflate(R.layout.color_scheme_customize, container, false);
        removeBackArrow();
        JulyTimersave save = saveExec.load(view.getContext());
        setButtonListeners();
        setButtonText(save);
        changeColors(save);
        setBackgroundImage(save);
        showAutomaticText(save.getDarkMode()[2]);
        return view;
    }

    private void removeBackArrow() {
        ActionBar actionBar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    private void setBackgroundImage(JulyTimersave save) {
        ImageView background = view.findViewById(R.id.customizeColorSchemeBackground);
        background.setAlpha((float) 0.6);
        if(save.getBackgroundImage() != null) {
            background.setImageBitmap(save.getBackgroundImage());
            background.setVisibility(View.VISIBLE);
        } else {
            background.setVisibility(View.INVISIBLE);
        }
    }

    private void setButtonListeners() {
        JulyTimersave save = saveExec.load(view.getContext());

        Button btBack = view.findViewById(R.id.customizeColorSchemeBackButton);
        Button btChangeDarkMode = view.findViewById(R.id.customizeColorSchemeChangeButton);
        Button btChangeDarkButton = view.findViewById(R.id.darkModeButtonColorButton);
        Button btChangeDarkText = view.findViewById(R.id.darkModeTextColorButton);
        Button btChangeDarkBackground = view.findViewById(R.id.darkModeBackgroundColorButton);
        Button btChangeBrightButton = view.findViewById(R.id.brightModeButtonColorButton);
        Button btChangeBrightText = view.findViewById(R.id.brightModeTextColorButton);
        Button btChangeBrightBackground = view.findViewById(R.id.brightModeBackgroundColorButton);

        int darkButtonColor = Color.parseColor(save.getDarkColorScheme()[0]);
        int darkTextColor = Color.parseColor(save.getDarkColorScheme()[1]);
        int darkBackgroundColor = Color.parseColor(save.getDarkColorScheme()[2]);
        int brightButtonColor = Color.parseColor(save.getBrightColorScheme()[0]);
        int brightTextColor = Color.parseColor(save.getBrightColorScheme()[1]);
        int brightBackgroundColor = Color.parseColor(save.getBrightColorScheme()[2]);

        btBack.setOnClickListener((View) -> requireActivity().onBackPressed());
        btChangeDarkMode.setOnClickListener((View) -> rotateColorScheme());


        btChangeBrightButton.setOnClickListener((View) -> {
            AmbilWarnaDialog colorPickerDialog = new AmbilWarnaDialog(view.getContext(), brightButtonColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    btChangeBrightButton.setBackgroundColor(color);
                    replace(1, 0, color);
                }
                @Override
                public void onCancel(AmbilWarnaDialog dialog) {}
            });
            colorPickerDialog.show();
        });

        btChangeBrightText.setOnClickListener((View) -> {
            AmbilWarnaDialog colorPickerDialog = new AmbilWarnaDialog(view.getContext(), brightTextColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    btChangeBrightText.setBackgroundColor(color);
                    replace(1, 1, color);
                }
                @Override
                public void onCancel(AmbilWarnaDialog dialog) {}
            });
            colorPickerDialog.show();
        });

        btChangeBrightBackground.setOnClickListener((View) -> {
            AmbilWarnaDialog colorPickerDialog = new AmbilWarnaDialog(view.getContext(), brightBackgroundColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    btChangeBrightBackground.setBackgroundColor(color);
                    replace(1, 2, color);
                }
                @Override
                public void onCancel(AmbilWarnaDialog dialog) {}
            });
            colorPickerDialog.show();
        });

        btChangeDarkButton.setOnClickListener((View) -> {
            AmbilWarnaDialog colorPickerDialog = new AmbilWarnaDialog(view.getContext(), darkButtonColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    btChangeDarkButton.setBackgroundColor(color);
                    replace(0, 0, color);
                }
                @Override
                public void onCancel(AmbilWarnaDialog dialog) {}
            });
            colorPickerDialog.show();
        });

        btChangeDarkText.setOnClickListener((View) -> {
            AmbilWarnaDialog colorPickerDialog = new AmbilWarnaDialog(view.getContext(), darkTextColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    btChangeDarkText.setBackgroundColor(color);
                    replace(0, 1, color);
                }
                @Override
                public void onCancel(AmbilWarnaDialog dialog) {}
            });
            colorPickerDialog.show();
        });

        btChangeDarkBackground.setOnClickListener((View) -> {
            AmbilWarnaDialog colorPickerDialog = new AmbilWarnaDialog(view.getContext(), darkBackgroundColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    btChangeDarkBackground.setBackgroundColor(color);
                    replace(0, 2, color);
                }
                @Override
                public void onCancel(AmbilWarnaDialog dialog) {}
            });
            colorPickerDialog.show();
        });
    }

    private void replace(int darkmode, int place, int color) {
        JulyTimersave save = saveExec.load(view.getContext());
        String colorString = String.format("#%06X", 0xFFFFFF & color);
        if(darkmode == 0) {
            String[] m = save.getDarkColorScheme();
            m[place] = colorString;
            save.setDarkColorScheme(m);
        } else {
            String[] m = save.getBrightColorScheme();
            m[place] = colorString;
            save.setBrightColorScheme(m);
        }
        saveExec.save(save, view.getContext());
    }


    private void rotateColorScheme() {
        JulyTimersave save = saveExec.load(view.getContext());
        Integer[] mode = save.getDarkMode();
        mode[2]++;
        if(mode[2] > 2) {
            mode[2] = 0;
        }
        save.setDarkMode(mode);
        saveExec.save(save, view.getContext());
        setButtonText(save);
        changeColors(save);
        showAutomaticText(mode[2]);
    }

    private void showAutomaticText(int mode) {
        if(mode == 0) {
            //TODO: Show the texts
        } else {
            //TODO: Hide the texts
        }
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

        int darkButtonColor = Color.parseColor(save.getDarkColorScheme()[0]);
        int darkTextColor = Color.parseColor(save.getDarkColorScheme()[1]);
        int darkBackgroundColor = Color.parseColor(save.getDarkColorScheme()[2]);
        int brightButtonColor = Color.parseColor(save.getBrightColorScheme()[0]);
        int brightTextColor = Color.parseColor(save.getBrightColorScheme()[1]);
        int brightBackgroundColor = Color.parseColor(save.getBrightColorScheme()[2]);

        Button btChangeDarkButton = view.findViewById(R.id.darkModeButtonColorButton);
        Button btChangeDarkText = view.findViewById(R.id.darkModeTextColorButton);
        Button btChangeDarkBackground = view.findViewById(R.id.darkModeBackgroundColorButton);
        Button btChangeBrightButton = view.findViewById(R.id.brightModeButtonColorButton);
        Button btChangeBrightText = view.findViewById(R.id.brightModeTextColorButton);
        Button btChangeBrightBackground = view.findViewById(R.id.brightModeBackgroundColorButton);

        btChangeDarkButton.setBackgroundColor(darkButtonColor);
        btChangeDarkText.setBackgroundColor(darkTextColor);
        btChangeDarkBackground.setBackgroundColor(darkBackgroundColor);
        btChangeBrightButton.setBackgroundColor(brightButtonColor);
        btChangeBrightText.setBackgroundColor(brightTextColor);
        btChangeBrightBackground.setBackgroundColor(brightBackgroundColor);
    }

    private void setColors(String[] ColorScheme) {
        int buttonColor = Color.parseColor(ColorScheme[0]);
        int textColor = Color.parseColor(ColorScheme[1]);
        int backgroundColor = Color.parseColor(ColorScheme[2]);

        Button btBack = view.findViewById(R.id.customizeColorSchemeBackButton);
        Button btChangeDarkMode = view.findViewById(R.id.customizeColorSchemeChangeButton);

        TextView brightModeLabel = view.findViewById(R.id.brightModeButtonsText);
        TextView darkModeLabel = view.findViewById(R.id.darkModeButtonsText);

        View layout = view.findViewById(R.id.customizeColorSchemeLayout);

        btBack.setBackgroundColor(buttonColor);
        btChangeDarkMode.setBackgroundColor(buttonColor);
        btBack.setTextColor(textColor);
        btChangeDarkMode.setTextColor(textColor);

        brightModeLabel.setBackgroundColor(buttonColor);
        darkModeLabel.setBackgroundColor(buttonColor);
        brightModeLabel.setTextColor(textColor);
        darkModeLabel.setTextColor(textColor);

        layout.setBackgroundColor(backgroundColor);
    }


    private void setButtonText(JulyTimersave save) {
        Button btSelectDarkMode = view.findViewById(R.id.customizeColorSchemeChangeButton);
        btSelectDarkMode.setText(StringCompiler.getCustomizeDarkModeButtonText(save, view.getContext()));
    }
}
