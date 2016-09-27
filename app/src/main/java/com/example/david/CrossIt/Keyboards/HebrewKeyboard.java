package com.example.david.CrossIt.Keyboards;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.david.CrossIt.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HebrewKeyboard extends Fragment {


    public HebrewKeyboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hebrew_keyboard, container, false);
    }

}
