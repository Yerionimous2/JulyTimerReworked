package com.example.julytimerreworked;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.julytimerreworked.databinding.ShowCustomizeBinding;

import java.util.Objects;


public class customizeShow extends Fragment {
    ShowCustomizeBinding binding;
    View view;

    /**
     * Diese Methode wird aufgerufen, um die Benutzeroberfläche für die Anzeige der Anpassungsoptionen anzuzeigen.
     *
     * @param inflater Der LayoutInflater, der verwendet wird, um die XML-Layoutdatei in die zugehörige
     *                View-Komponente zu inflaten.
     * @param container Die Elternansicht, in die die fragment_layout-Datei inflatet wird.
     * @param savedInstanceState Wenn die Aktivität erneut erstellt wird (zum Beispiel bei einer
     *                           Bildschirmrotation), enthält dieser Parameter die Daten, die von der
     *                           Aktivität zuvor gespeichert wurden.
     * @return Die erstellte Ansicht für die Anzeige der Anpassungsoptionen.
     */
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
        setBackgroundImage(save);

        return view;
    }

    private void setBackgroundImage(JulyTimersave save) {
        ImageView background = view.findViewById(R.id.customizeTimeShowBackground);
        background.setAlpha((float) 0.6);
        if(save.getBackgroundImage() != null) {
            background.setImageBitmap(save.getBackgroundImage());
            background.setVisibility(View.VISIBLE);
        } else {
            background.setVisibility(View.INVISIBLE);
        }
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
     * @param ColorScheme Ein String-Array, das die Farben für die Schaltflächen, den Text und den Hintergrund enthält.
     */
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

    /**
     * Initialisiert die Checkboxen basierend auf den gespeicherten Einstellungen.
     * Setzt jeweils einen Haken, falls das Zeitintervall im Moment gezeigt wird.
     *
     * @param save Das JulyTimersave-Objekt, das die gespeicherten Daten enthält.
     */
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

    /**
     * Setzt den OnClickListener für die Zurück-Schaltfläche.
     */
    private void setButtonListeners() {
        Button btBack = view.findViewById(R.id.customizeTimeShowBack);
        btBack.setOnClickListener((View) -> {
            requireActivity().onBackPressed();
        });
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
}
