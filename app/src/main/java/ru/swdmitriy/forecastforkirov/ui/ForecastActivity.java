package ru.swdmitriy.forecastforkirov.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.io.UnsupportedEncodingException;

import ru.swdmitriy.forecastforkirov.R;
import ru.swdmitriy.forecastforkirov.logger.ForecastLogger;
import ru.swdmitriy.forecastforkirov.model.Forecast;
import ru.swdmitriy.forecastforkirov.service.ForecastRetrofitSpiceRequest;

/**
 * Created by dmitriy on 28.07.15.
 */
public class ForecastActivity extends BaseForecastActivity{
    private TextView tempView;
    private TextView timeStampView;
    private ForecastRetrofitSpiceRequest forecastRequest;
    private static final String POST_PARAMS = "27199";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        tempView = (TextView)findViewById(R.id.tempView);
        timeStampView = (TextView)findViewById(R.id.timeStampView);
        forecastRequest = new ForecastRetrofitSpiceRequest(POST_PARAMS);
        Log.d(ForecastLogger.TAG, "MainActivity onCreate()");
    }


    public void onBtnRefreshClick(View v){
        Log.d(ForecastLogger.TAG, "onBtnRefreshClick()");
        refresh();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(ForecastLogger.TAG, "MainActivity onStart()");
        getSpiceManager().execute(forecastRequest, "forecast", DurationInMillis.ONE_MINUTE, new ForecastRequestListener());
    }

    private void refresh() {
        Log.d(ForecastLogger.TAG, "getSpiceManager().execute");
        getSpiceManager().execute(forecastRequest, "forecast", DurationInMillis.ONE_MINUTE, new ForecastRequestListener());
    }


    private void updateForecast(Forecast forecast) throws UnsupportedEncodingException {
        tempView.setText(forecast.getTemperature().getValue() +" °C");
        timeStampView.setText(new String(forecast.getTemperature().getTime().split(" ")[0].getBytes("UTF-8"), "UTF-8") + " " + forecast.getTemperature().getTime().split(" ")[1]);
    }
    private class ForecastRequestListener implements RequestListener<Forecast> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(ForecastActivity.this, "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(Forecast forecast) {
            Log.d(ForecastLogger.TAG, "onRequestSuccess()");
            try {
                updateForecast(forecast);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
