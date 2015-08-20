package ru.swdmitriy.forecastforkirov.activity;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import ru.swdmitriy.forecastforkirov.R;
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
        setContentView(R.layout.main_layout);
        tempView = (TextView)findViewById(R.id.tempView);
        timeStampView = (TextView)findViewById(R.id.timeStampView);
        forecastRequest = new ForecastRetrofitSpiceRequest(POST_PARAMS);
    }


    public void onBtnRefreshClick(View v){
        refresh();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSpiceManager().execute(forecastRequest, "forecast", DurationInMillis.ONE_MINUTE, new ForecastRequestListener());
    }

    private void refresh() {
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
            try {
                updateForecast(forecast);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
