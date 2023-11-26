package com.example.julytimerreworked;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;



public class FirstFragment extends Fragment {

    View view;

    /**
     * Diese Methode wird aufgerufen, um die Benutzeroberfläche für das erste Fragment anzuzeigen.
     *
     * @param inflater Der LayoutInflater, der verwendet wird, um die XML-Layoutdatei in die zugehörige
     *                View-Komponente zu inflaten.
     * @param container Die Elternansicht, in die die fragment_layout-Datei inflatet wird.
     * @param savedInstanceState Wenn die Aktivität erneut erstellt wird (zum Beispiel bei einer
     *                           Bildschirmrotation), enthält dieser Parameter die Daten, die von der
     *                           Aktivität zuvor gespeichert wurden.
     * @return Die erstellte Ansicht für das erste Fragment.
     */
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
    private void setColors(String[] colorScheme) {
        int buttonColor = Color.parseColor(colorScheme[0]);
        int textColor = Color.parseColor(colorScheme[1]);
        int backgroundColor = Color.parseColor(colorScheme[2]);

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

    /**
     * Setzt die OnClickListener für die Schaltflächen im Hauptmenü.
     */
    private void setButtonListeners() {
        Button btCustomizeShowTime = view.findViewById(R.id.mainSettingsTimeShow);
        Button btBack = view.findViewById(R.id.mainSettingsBack);
        Button btChangeDates = view.findViewById(R.id.mainSettingsChangeDates);
        Button btPickBackgroundImage = view.findViewById(R.id.mainSettingsChangeBackground);
        Button btReset = view.findViewById(R.id.mainSettingsReset);
        Button btShowMileStones = view.findViewById(R.id.mainSettingsShowMilestones);
        Button btCustomizeColorScheme = view.findViewById(R.id.mainSettingsCustomizeDarkMode);

        btCustomizeShowTime.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_FirstFragment_to_customizeShow));
        btChangeDates.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_FirstFragment_to_changeDates));
        btShowMileStones.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_FirstFragment_to_mileStones));
        btCustomizeColorScheme.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_FirstFragment_to_customizeColorScheme));
        btBack.setOnClickListener((View) -> requireActivity().onBackPressed());

        btReset.setOnClickListener((View) -> {
            JulyTimersave save = saveExec.reset(view.getContext());
            saveExec.save(save, view.getContext());
            save = saveExec.load(view.getContext());
            changeColors(save);
            ImageView background = view.findViewById(R.id.mainSettingsBackground);
            background.setImageBitmap(null);
            background.setVisibility(android.view.View.INVISIBLE);
        });

        btPickBackgroundImage.setOnClickListener(view -> {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(i, "getString(R.string.pick_Image)"), 1);
        });
    }

    /**
     * Wird aufgerufen, wenn das Ergebnis der Bildauswahl-Aktivität zurückgegeben wird.
     * Setzt das Hintergrundbild und speichert es.
     *
     * @param requestCode Der Code, der der gestarteten Aktivität zugeordnet ist.
     * @param resultCode Der Ergebniscode der beendeten Aktivität.
     * @param data Die Daten, die von der beendeten Aktivität zurückgegeben wurden.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        JulyTimersave save = saveExec.load(view.getContext());

        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {

                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {

                    ImageView backgroundImage = view.findViewById(R.id.mainSettingsBackground);
                    backgroundImage.setImageURI(selectedImageUri);
                    backgroundImage.setVisibility(View.VISIBLE);
                    backgroundImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    BitmapDrawable BitmapBackground = (BitmapDrawable) backgroundImage.getDrawable();
                    Bitmap bitmap = BitmapBackground.getBitmap();
                    save.setBackgroundImage(bitmap);
                    saveExec.save(save, view.getContext());
                }
            }
        }
    }



    /**
     * Setzt das Hintergrundbild für die Hauptmenüansicht basierend auf den gespeicherten Daten.
     *
     * @param save Das JulyTimersave-Objekt, das die gespeicherten Daten enthält.
     */
    private void setBackgroundImage(JulyTimersave save) {
        ImageView background = view.findViewById(R.id.mainSettingsBackground);
        background.setAlpha((float) 0.6);
        if(save.getBackgroundImage() != null) {
            background.setImageBitmap(save.getBackgroundImage());
            background.setVisibility(View.VISIBLE);
        } else {
            background.setVisibility(View.INVISIBLE);
        }
    }

}