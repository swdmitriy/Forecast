package ru.swdmitriy.forecastforkirov.service;

import android.app.Application;

import com.octo.android.robospice.okhttp.OkHttpSpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;

/**
 * Created by dmitriy on 11.09.15.
 */
public class CitySearchService extends OkHttpSpiceService {
    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        return new CacheManager();
    }
}
