package com.example.julytimerreworked;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

/**
 * Die Klasse `customizeColorScheme` ist ein Fragment, das es dem Benutzer ermöglicht, das Farbschema
 * und die Erscheinungseinstellungen für die Anwendung "July Timer" anzupassen.
 *
 * Der Benutzer kann verschiedene Farbeigenschaften wie Knopffarben, Textfarben und Hintergrundfarben anpassen.
 * Darüber hinaus kann der Benutzer die Start- und Endzeiten für den Dunkelmodus festlegen. Die Klasse enthält
 * Funktionen zum Anzeigen eines Farbauswahl-Dialogs, zum Bearbeiten der Zeitauswahl über einen TimePickerDialog
 * und zum Aktualisieren der Benutzeroberfläche basierend auf den Benutzerpräferenzen.
 *
 * Diese Klasse erweitert die Fragment-Klasse und ist mit dem Layout `color_scheme_customize.xml` verknüpft.
 *
 * Funktionen:
 * - Anpassung von Dunkel- und Hell-Farbschemata
 * - Festlegen von Start- und Endzeiten für den Dunkelmodus
 * - Anzeigen und Aktualisieren von Benutzeroberflächenelementen basierend auf Benutzerpräferenzen
 * - Behandlung von Farbauswahl-Dialogen für verschiedene Benutzeroberflächenelemente
 *
 * Verwendung:
 * - Fügen Sie dieses Fragment in die Aktivität oder das Layout ein, in dem die Farbanpassung erforderlich ist.
 * - Nutzen Sie die bereitgestellten Methoden, um Listener einzurichten, Farbänderungen zu handhaben und
 *   Benutzeroberflächenelemente zu aktualisieren.
 *
 * Hinweis: Stellen Sie sicher, dass die entsprechende Layout-Datei (`color_scheme_customize.xml`) ordnungsgemäß
 * gestaltet ist, um die in dieser Klasse referenzierten Benutzeroberflächenelemente aufzunehmen.
 *
 * @author Yerionimous
 */
public class customizeColorScheme extends Fragment {

    View view;

    /**
     * Erstellt und liefert die Benutzeroberfläche für die Farbschema-Anpassung.
     * Initialisiert die UI-Elemente, setzt Listener und aktualisiert die Anzeige basierend auf gespeicherten
     * Benutzerpräferenzen.
     *
     * @param inflater           Der LayoutInflater, der zum Aufblasen des Layouts verwendet wird.
     * @param container          Die Ansicht, in die das Fragment eingefügt wird.
     * @param savedInstanceState Die gespeicherten Zustandsinformationen, falls vorhanden.
     * @return Die erstellte View für das Fragment.
     */
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        view = inflater.inflate(R.layout.color_scheme_customize, container, false);
        removeBackArrow();
        JulyTimersave save = saveExec.load(view.getContext());
        setButtonListeners();
        setTexts(save);
        changeColors(save);
        setBackgroundImage(save);
        showAutomaticText(save.getDarkMode()[2]);
        return view;
    }

    /**
     * Setzt die Texte für die UI-Elemente basierend auf den gespeicherten Benutzerpräferenzen.
     *
     * @param save Das JulyTimersave-Objekt, das die gespeicherten Präferenzen enthält.
     */
    private void setTexts(JulyTimersave save) {
        setButtonText(save);
        setTextViewTexts(save);
    }

    /**
     * Setzt die Texte für die TextView-Elemente basierend auf den gespeicherten Benutzerpräferenzen.
     *
     * @param save Das JulyTimersave-Objekt, das die gespeicherten Präferenzen enthält.
     */
    private void setTextViewTexts(JulyTimersave save) {
        TextView darkModeStartTimeLabel = view.findViewById(R.id.darkModeStartTimeLabel);
        TextView darkModeEndTimeLabel = view.findViewById(R.id.darkModeEndTimeLabel);

        darkModeStartTimeLabel.setText(StringCompiler.getStartDarkModeText(save, view.getContext()));
        darkModeEndTimeLabel.setText(StringCompiler.getEndDarkModeText(save, view.getContext()));
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
     * Setzt das Hintergrundbild in der ImageView basierend auf den gespeicherten Benutzerpräferenzen.
     *
     * @param save Das JulyTimersave-Objekt, das die gespeicherten Präferenzen enthält.
     */
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

    // Füge dies innerhalb deiner Aktivität oder deines Fragments hinzu, wo du den Code oben verwendest
    TimePickerDialog.OnTimeSetListener timeSetListenerStart = (view, hourOfDay, minute) -> {
        JulyTimersave save = saveExec.load(view.getContext());
        Integer[] m = save.getDarkMode();
        m[0] = hourOfDay;
        save.setDarkMode(m);
        saveExec.save(save, view.getContext());
        setTextViewTexts(save);
        changeColors(save);
    };

    // Füge dies innerhalb deiner Aktivität oder deines Fragments hinzu, wo du den Code oben verwendest
    TimePickerDialog.OnTimeSetListener timeSetListenerEnd = (view, hourOfDay, minute) -> {
        JulyTimersave save = saveExec.load(view.getContext());
        Integer[] m = save.getDarkMode();
        m[1] = hourOfDay;
        save.setDarkMode(m);
        saveExec.save(save, view.getContext());
        setTextViewTexts(save);
        changeColors(save);
    };

    /**
     * Zeigt den TimePickerDialog für den Startzeitpunkt an und aktualisiert die gespeicherten Präferenzen
     * basierend auf der ausgewählten Zeit.
     *
     * @param listener   Der TimePickerDialog.OnTimeSetListener für den Startzeitpunkt.
     * @param startHour  Die Stunde des Startzeitpunkts.
     */
    private void showTimePickerDialog(TimePickerDialog.OnTimeSetListener listener, int startHour) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                requireContext(),
                listener,
                startHour, // Stunde
                0, // Minute (Ignoriert, da wir nur Stunden anzeigen)
                true // 24-Stunden-Format verwenden
        );
        timePickerDialog.show();
    }

    /**
     * Setzt die Listener für die Buttons in der Benutzeroberfläche, um Aktionen bei Benutzerinteraktionen
     * auszulösen, wie das Ändern der Farbschemata oder das Öffnen des TimePickerDialogs.
     */
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
        TextView darkModeStartTimeLabel = view.findViewById(R.id.darkModeStartTimeLabel);
        TextView darkModeEndTimeLabel = view.findViewById(R.id.darkModeEndTimeLabel);

        darkModeStartTimeLabel.setOnClickListener(v -> {
            // Öffne den TimePickerDialog für den Startzeitpunkt
            showTimePickerDialog(timeSetListenerStart, save.getDarkMode()[0]);
        });

        darkModeEndTimeLabel.setOnClickListener(v -> {
            // Öffne den TimePickerDialog für den Endzeitpunkt
            showTimePickerDialog(timeSetListenerEnd, save.getDarkMode()[1]);
        });

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

    /**
     * Ersetzt eine Farbe in den gespeicherten Farbschemata und speichert die aktualisierten Präferenzen.
     *
     * @param darkmode Das Farbschema, entweder für den Dunkelmodus (0) oder den Hellmodus (1).
     * @param place    Die Position der zu ersetzenden Farbe im Farbschema.
     * @param color    Die neue Farbe, die gesetzt werden soll.
     */
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

    /**
     * Rotiert das Farbschema um 1 und aktualisiert die Benutzeroberfläche basierend auf den neuen Präferenzen.
     */
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

    /**
     * Zeigt oder versteckt die Texte für die automatische Anzeige basierend auf dem ausgewählten Modus.
     *
     * @param mode Der ausgewählte Modus (0 für automatisch, 1 für hell, 2 für dunkel).
     */
    private void showAutomaticText(int mode) {
        TextView darkModeStartTimeLabel = view.findViewById(R.id.darkModeStartTimeLabel);
        TextView darkModeEndTimeLabel = view.findViewById(R.id.darkModeEndTimeLabel);
        if(mode == 0) {
            darkModeStartTimeLabel.setVisibility(View.VISIBLE);
            darkModeEndTimeLabel.setVisibility(View.VISIBLE);
        } else {
            darkModeStartTimeLabel.setVisibility(View.INVISIBLE);
            darkModeEndTimeLabel.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Ändert die Farben in der Benutzeroberfläche basierend auf den gespeicherten Präferenzen.
     *
     * @param save Das JulyTimersave-Objekt, das die gespeicherten Präferenzen enthält.
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

    /**
     * Setzt die Farben für die UI-Elemente basierend auf dem übergebenen Farbschema.
     *
     * @param ColorScheme Ein String-Array mit den Farbwerten (Button, Text, Hintergrund).
     */
    private void setColors(String[] ColorScheme) {
        int buttonColor = Color.parseColor(ColorScheme[0]);
        int textColor = Color.parseColor(ColorScheme[1]);
        int backgroundColor = Color.parseColor(ColorScheme[2]);

        Button btBack = view.findViewById(R.id.customizeColorSchemeBackButton);
        Button btChangeDarkMode = view.findViewById(R.id.customizeColorSchemeChangeButton);

        TextView brightModeLabel = view.findViewById(R.id.brightModeButtonsText);
        TextView darkModeLabel = view.findViewById(R.id.darkModeButtonsText);
        TextView darkModeStartTimeLabel = view.findViewById(R.id.darkModeStartTimeLabel);
        TextView darkModeEndTimeLabel = view.findViewById(R.id.darkModeEndTimeLabel);

        View layout = view.findViewById(R.id.customizeColorSchemeLayout);

        ActionBar actionBar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();

        if(actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(backgroundColor));
        }

        btBack.setBackgroundColor(buttonColor);
        btChangeDarkMode.setBackgroundColor(buttonColor);
        btBack.setTextColor(textColor);
        btChangeDarkMode.setTextColor(textColor);

        brightModeLabel.setBackgroundColor(buttonColor);
        darkModeLabel.setBackgroundColor(buttonColor);
        darkModeStartTimeLabel.setBackgroundColor(buttonColor);
        darkModeEndTimeLabel.setBackgroundColor(buttonColor);
        brightModeLabel.setTextColor(textColor);
        darkModeLabel.setTextColor(textColor);
        darkModeStartTimeLabel.setTextColor(textColor);
        darkModeEndTimeLabel.setTextColor(textColor);

        layout.setBackgroundColor(backgroundColor);
    }

    /**
     * Setzt den Text des Button-Elements basierend auf den gespeicherten Präferenzen.
     *
     * @param save Das JulyTimersave-Objekt, das die gespeicherten Präferenzen enthält.
     */
    private void setButtonText(JulyTimersave save) {
        Button btSelectDarkMode = view.findViewById(R.id.customizeColorSchemeChangeButton);
        btSelectDarkMode.setText(StringCompiler.getCustomizeDarkModeButtonText(save, view.getContext()));
    }
}
