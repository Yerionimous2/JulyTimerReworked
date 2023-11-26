package com.example.julytimerreworked;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class customizeColorScheme extends Fragment {

    View view;
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        view = inflater.inflate(R.layout.color_scheme_customize, container, false);

        JulyTimersave save = saveExec.load(view.getContext());
        setTexts(save);

        return view;
    }

    private void setTexts(JulyTimersave save) {
        setButtonText(save);
        setTextViewTexts(save);
    }

    private void setTextViewTexts(JulyTimersave save) {
        //TODO: implement
    }

    private void setButtonText(JulyTimersave save) {
        //TODO: implement
    }
}
