package ru.swdmitriy.forecastforkirov.service;

import android.util.Log;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import ru.swdmitriy.forecastforkirov.logger.ForecastLogger;
import ru.swdmitriy.forecastforkirov.model.Forecast;

/**
 * Created by dmitriy on 20.08.15.
 */
public class ForecastRetrofitSpiceRequest extends RetrofitSpiceRequest<Forecast, PogodaKirov> {


    private String city;
    public ForecastRetrofitSpiceRequest(String city) {
        super(Forecast.class, PogodaKirov.class);
        Log.d(ForecastLogger.TAG, "ForecastRetrofitSpiceRequest()");
        this.city = city;
    }
    @Override
    public Forecast loadDataFromNetwork() throws Exception {
        Log.d(ForecastLogger.TAG, "loadDataFromNetwork()");
        return getService().forecast(city);
    }

}
