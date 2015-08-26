package ru.swdmitriy.forecastforkirov.service;

import android.util.Log;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import ru.swdmitriy.forecastforkirov.logger.ForecastLogger;

/**
 * Created by dmitriy on 19.08.15.
 */
public class ForecastService extends RetrofitGsonSpiceService {

    private final static String BASE_URL = "http://pogoda.kirov.ru";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(ForecastLogger.TAG, "service onCreate()");
        addRetrofitInterface(PogodaKirov.class);
    }
    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }
}
