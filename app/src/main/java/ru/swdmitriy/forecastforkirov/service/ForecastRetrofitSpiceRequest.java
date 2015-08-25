package ru.swdmitriy.forecastforkirov.service;

import android.util.Log;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import ru.swdmitriy.forecastforkirov.model.Forecast;

/**
 * Created by dmitriy on 20.08.15.
 */
public class ForecastRetrofitSpiceRequest extends RetrofitSpiceRequest<Forecast, PogodaKirov> {


    private static final String TAG = "ForecastLog";
    private String city;
    public ForecastRetrofitSpiceRequest(String city) {
        super(Forecast.class, PogodaKirov.class);
        Log.d(TAG, "ForecastRetrofitSpiceRequest()");
        this.city = city;
    }
    @Override
    public Forecast loadDataFromNetwork() throws Exception {
        Log.d(TAG, "loadDataFromNetwork()");
        return getService().forecast(city);
    }

}
