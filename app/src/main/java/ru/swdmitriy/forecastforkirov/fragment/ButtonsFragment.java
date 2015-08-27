package ru.swdmitriy.forecastforkirov.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.swdmitriy.forecastforkirov.R;

/**
 * Created by dmitriy on 25.08.15.
 */
public class ButtonsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buttons, container, false);
    }
}
