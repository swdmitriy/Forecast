package ru.swdmitriy.forecastforkirov.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.io.UnsupportedEncodingException;

import ru.swdmitriy.forecastforkirov.R;
import ru.swdmitriy.forecastforkirov.logger.ForecastLogger;
import ru.swdmitriy.forecastforkirov.model.Forecast;
import ru.swdmitriy.forecastforkirov.service.ForecastRetrofitSpiceRequest;
import ru.swdmitriy.forecastforkirov.service.ForecastService;

/**
 * Created by dmitriy on 25.08.15.
 */
public class CurrentFragment extends Fragment {
    public static final String TAG = "CurrentFragmentTag";
    private SpiceManager spiceManager = new SpiceManager(ForecastService.class);
    private TextView tempView;
    private TextView timeStampView;
    private ForecastRetrofitSpiceRequest forecastRequest;
    private static final String POST_PARAMS = "27199";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(ForecastLogger.TAG, "CurrentFragment onCreateView()");
        return inflater.inflate(R.layout.fragment_current, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(ForecastLogger.TAG, "CurrentFragment onViewCreated()");
        tempView = (TextView)getView().findViewById(R.id.tempView);
        timeStampView = (TextView)getView().findViewById(R.id.timeStampView);
        forecastRequest = new ForecastRetrofitSpiceRequest(POST_PARAMS);

        CurrentFragment.this.getActivity().setProgressBarIndeterminateVisibility(true);
        getSpiceManager().execute(forecastRequest, "forecast", DurationInMillis.ONE_MINUTE, new ForecastRequestListener());
    }



    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(getActivity());
        Log.d(ForecastLogger.TAG, "CurrentFragment onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(ForecastLogger.TAG, "CurrentFragment onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(ForecastLogger.TAG, "CurrentFragment onPause()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(ForecastLogger.TAG, "CurrentFragment onDestroy()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(ForecastLogger.TAG, "CurrentFragment onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(ForecastLogger.TAG, "CurrentFragment onDetach()");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(ForecastLogger.TAG, "CurrentFragment onAttach()");
    }

    @Override
    public void onStop() {
        super.onStop();
        spiceManager.shouldStop();
        Log.d(ForecastLogger.TAG, "CurrentFragment onStop()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(ForecastLogger.TAG, "CurrentFragment onCreate");
    }

    private SpiceManager getSpiceManager() {
        return spiceManager;
    }

    private void updateForecast(Forecast forecast) throws UnsupportedEncodingException {
        tempView.setText(forecast.getTemperature().getValue() +" °C");
        timeStampView.setText(new String(forecast.getTemperature().getTime().split(" ")[0].getBytes("UTF-8"), "UTF-8") + " " + forecast.getTemperature().getTime().split(" ")[1]);
    }
    private class ForecastRequestListener implements RequestListener<Forecast> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(Forecast forecast) {
            Log.d(ForecastLogger.TAG, "onRequestSuccess()");
            try {
                updateForecast(forecast);
                CurrentFragment.this.getActivity().setProgressBarIndeterminateVisibility(true);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
