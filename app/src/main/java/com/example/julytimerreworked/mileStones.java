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

/**
 * Das Fragment "mileStones" repräsentiert die Meilenstein-Ansicht der Anwendung.
 */
public class mileStones extends Fragment {
    View view;

    /**
     * Wird aufgerufen, um die Benutzeroberfläche für das Fragment zu erstellen.
     *
     * @param inflater           Der LayoutInflater, der verwendet wird, um die Ansicht zu erstellen.
     * @param container          Der ViewGroup, in die die Ansicht eingefügt wird.
     * @param savedInstanceState Ein Bundle, das den gespeicherten Zustand enthält.
     * @return Die erstellte Ansicht.
     */
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        view = inflater.inflate(R.layout.show_milestones, container, false);
        removeBackArrow();

        JulyTimersave save = saveExec.load(view.getContext());

        setButtonListeners();
        setBackgroundImage(save);
        changeColors(save);

        return view;
    }

    /**
     * Entfernt den Zurück-Pfeil aus der ActionBar.
     */
    private void removeBackArrow() {
        ActionBar actionBar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    /**
     * Setzt das Hintergrundbild der Ansicht basierend auf den gespeicherten Daten.
     *
     * @param save Ein JulyTimersave-Objekt, das die gespeicherten Daten enthält.
     */
    private void setBackgroundImage(JulyTimersave save) {
        ImageView background = view.findViewById(R.id.showMileStonesBackground);
        background.setAlpha((float) 0.6);
        if(save.getBackgroundImage() != null) {
            background.setImageBitmap(save.getBackgroundImage());
            background.setVisibility(View.VISIBLE);
        } else {
            background.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Setzt die OnClickListener für die "Zurück"-Schaltfläche.
     */
    private void setButtonListeners() {
        Button btBack = view.findViewById(R.id.showMileStonesBackButton);
        btBack.setOnClickListener((View) -> requireActivity().onBackPressed());
    }

    /**
     * Ändert die Farbschemata der Benutzeroberfläche basierend auf den gespeicherten Daten.
     * Wählt das richtige Farbchema und sendet es an setColors, welche die Farbänderung durchführt.
     *
     * @param save Das JulyTimersave-Objekt, das die gespeicherten Daten enthält.
     */
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

    /**
     * Setzt die Farben der UI-Elemente basierend auf dem übergebenen Farbschema.
     *
     * @param colorScheme Ein String-Array, das die Farben für die Schaltflächen, den Text und den Hintergrund enthält.
     */
    private void setColors(String[]colorScheme) {
        int buttonColor = Color.parseColor(colorScheme[0]);
        int textColor = Color.parseColor(colorScheme[1]);
        int backgroundColor = Color.parseColor(colorScheme[2]);

        int doneButtonColor = Color.parseColor("#000000");
        int doneTextColor = Color.parseColor("#FFFFFF"); //TODO: think hard about the colors.

        JulyTimersave save = saveExec.load(view.getContext());
        double percent = new xyzSet(save).getPercent();

        Button btBack = view.findViewById(R.id.showMileStonesBackButton);

        TextView oneQuarter = view.findViewById(R.id.showMileStoneOneQuarter);
        TextView oneThird = view.findViewById(R.id.showMileStoneOneThird);
        TextView oneHalf = view.findViewById(R.id.showMileStoneOneHalf);
        TextView twoThirds = view.findViewById(R.id.showMileStoneTwoThirds);
        TextView threeQuarter = view.findViewById(R.id.showMileStoneThreeQuarters);
        TextView ninetyPercent = view.findViewById(R.id.showMileStoneNinetyPercent);
        TextView done = view.findViewById(R.id.showMileStoneDone);

        View background = view.findViewById(R.id.showMileStonesLayout);

        btBack.setBackgroundColor(buttonColor);
        btBack.setTextColor(textColor);

        if(timeExec.done(percent, 25)) {
            oneQuarter.setBackgroundColor(doneButtonColor);
            oneQuarter.setTextColor(doneTextColor);
        } else {
            oneQuarter.setBackgroundColor(buttonColor);
            oneQuarter.setTextColor(textColor);
        }
        if(timeExec.done(percent, 100/3)) {
            oneThird.setBackgroundColor(doneButtonColor);
            oneThird.setTextColor(doneTextColor);
        } else {
            oneThird.setBackgroundColor(buttonColor);
            oneThird.setTextColor(textColor);
        }
        if(timeExec.done(percent, 50)) {
            oneHalf.setBackgroundColor(doneButtonColor);
            oneHalf.setTextColor(doneTextColor);
        } else {
            oneHalf.setBackgroundColor(buttonColor);
            oneHalf.setTextColor(textColor);
        }
        if(timeExec.done(percent, 200/3)) {
            twoThirds.setBackgroundColor(doneButtonColor);
            twoThirds.setTextColor(doneTextColor);
        } else {
            twoThirds.setBackgroundColor(buttonColor);
            twoThirds.setTextColor(textColor);
        }
        if(timeExec.done(percent, 75)) {
            threeQuarter.setBackgroundColor(doneButtonColor);
            threeQuarter.setTextColor(doneTextColor);
        } else {
            threeQuarter.setBackgroundColor(buttonColor);
            threeQuarter.setTextColor(textColor);
        }
        if(timeExec.done(percent, 90)) {
            ninetyPercent.setBackgroundColor(doneButtonColor);
            ninetyPercent.setTextColor(doneTextColor);
        } else {
            ninetyPercent.setBackgroundColor(buttonColor);
            ninetyPercent.setTextColor(textColor);
        }
        if(timeExec.done(percent, 100)) {
            done.setBackgroundColor(doneButtonColor);
            done.setTextColor(doneTextColor);
        } else {
            done.setBackgroundColor(buttonColor);
            done.setTextColor(textColor);
        }

        background.setBackgroundColor(backgroundColor);
    }
}
