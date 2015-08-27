package ru.swdmitriy.forecastforkirov.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.swdmitriy.forecastforkirov.R;
import ru.swdmitriy.forecastforkirov.logger.ForecastLogger;

/**
 * Created by dmitriy on 25.08.15.
 */
public class ForecastFragment extends Fragment {
    public static final String TAG = "ForecastFragmentTag";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forecast, null);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(ForecastLogger.TAG, "ForecastFragment onViewCreated()");
    }



    @Override
    public void onStart() {
        super.onStart();
        Log.d(ForecastLogger.TAG, "ForecastFragment onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(ForecastLogger.TAG, "ForecastFragment onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(ForecastLogger.TAG, "ForecastFragment onPause()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(ForecastLogger.TAG, "ForecastFragment onDestroy()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(ForecastLogger.TAG, "ForecastFragment onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(ForecastLogger.TAG, "ForecastFragment onDetach()");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(ForecastLogger.TAG, "ForecastFragment onAttach()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(ForecastLogger.TAG, "ForecastFragment onStop()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(ForecastLogger.TAG, "ForecastFragment onCreate");
    }
}
