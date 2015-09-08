package ru.swdmitriy.forecastforkirov.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;

import ru.swdmitriy.forecastforkirov.R;
import ru.swdmitriy.forecastforkirov.adapter.ForecastAdapter;
import ru.swdmitriy.forecastforkirov.logger.ForecastLogger;
import ru.swdmitriy.forecastforkirov.model.Time;
import ru.swdmitriy.forecastforkirov.model.WeatherData;
import ru.swdmitriy.forecastforkirov.service.WeatherDataSpiceService;
import ru.swdmitriy.forecastforkirov.service.WeatherDataXmlRequest;
import ru.swdmitriy.forecastforkirov.util.Location;

/**
 * Created by dmitriy on 25.08.15.
 */
public class ForecastFragment extends Fragment {
    public static final String TAG = "ForecastFragmentTag";
    private SpiceManager spiceManager = new SpiceManager(WeatherDataSpiceService.class );
    private WeatherDataXmlRequest forecastRequest;



    private ListView forecastListView;

    public interface ReturnEventListener {
        public void returnEvent();
    }

    ReturnEventListener returnEventListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forecast, null);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(ForecastLogger.TAG, "ForecastFragment onViewCreated()");
        forecastListView = (ListView)getView().findViewById(R.id.forecastListView);
        Button button = (Button) getView().findViewById(R.id.btn_return);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                returnEventListener.returnEvent();
            }
        });
        forecastRequest = new WeatherDataXmlRequest(Location.getCountry(), Location.getRegion(), Location.getCity());
        getSpiceManager().getFromCache(WeatherData.class, "Forecast", DurationInMillis.ONE_MINUTE, new ForecastRequestListener());
        getSpiceManager().execute(forecastRequest, "Forecast", DurationInMillis.ONE_MINUTE, new ForecastRequestListener());
    }


    public final class ForecastRequestListener implements RequestListener< WeatherData > {

        private ForecastAdapter forecastAdapter;

        @Override
        public void onRequestFailure( SpiceException spiceException ) {
            Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess( final WeatherData weatherData ) {
            if (weatherData!=null) {
                ArrayList<Time> times = new ArrayList<>(weatherData.getTime());
                forecastAdapter = new ForecastAdapter(getActivity(), times);
                forecastListView.setAdapter(forecastAdapter);
            }
        }
    }

    @Override
    public void onStart() {
        spiceManager.start(getActivity());
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
        try {
            returnEventListener = (ReturnEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public void onStop() {
        spiceManager.shouldStop();
        super.onStop();
        Log.d(ForecastLogger.TAG, "ForecastFragment onStop()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(ForecastLogger.TAG, "ForecastFragment onCreate");
    }

    private SpiceManager getSpiceManager() {
        return spiceManager;
    }
}
