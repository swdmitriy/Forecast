package ru.swdmitriy.forecastforkirov.service;

import android.app.Application;

import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.string.InFileStringObjectPersister;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

/**
 * Created by dmitriy on 19.08.15.
 */
public class ForecastService extends RetrofitGsonSpiceService {

    private final static String BASE_URL = "http://pogoda.kirov.ru";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(PogodaKirov.class);
    }
    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }
}
