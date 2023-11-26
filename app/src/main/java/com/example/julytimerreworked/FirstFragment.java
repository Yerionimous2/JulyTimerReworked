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

    private void setButtonListeners() {
        Button btCustomizeShowTime = view.findViewById(R.id.mainSettingsTimeShow);
        Button btBack = view.findViewById(R.id.mainSettingsBack);
        Button btChangeDates = view.findViewById(R.id.mainSettingsChangeDates);
        Button btPickBackgroundImage = view.findViewById(R.id.mainSettingsChangeBackground);
        Button btReset = view.findViewById(R.id.mainSettingsReset);
        Button btShowMileStones = view.findViewById(R.id.mainSettingsShowMilestones);

        btCustomizeShowTime.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_FirstFragment_to_customizeShow));
        btChangeDates.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_FirstFragment_to_changeDates));
        btShowMileStones.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_FirstFragment_to_mileStones));
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        JulyTimersave save = saveExec.load(view.getContext());

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == 1) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
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

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}