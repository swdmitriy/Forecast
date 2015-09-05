package ru.swdmitriy.forecastforkirov.service;

import android.app.Application;

import com.octo.android.robospice.SpringAndroidSpiceService;
import com.octo.android.robospice.XmlSpringAndroidSpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.memory.LruCacheStringObjectPersister;
import com.octo.android.robospice.persistence.ormlite.InDatabaseObjectPersisterFactory;
import com.octo.android.robospice.persistence.ormlite.RoboSpiceDatabaseHelper;
import com.octo.android.robospice.persistence.springandroid.json.jackson2.Jackson2ObjectPersister;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import ru.swdmitriy.forecastforkirov.model.Product;
import ru.swdmitriy.forecastforkirov.model.Time;
import ru.swdmitriy.forecastforkirov.model.WeatherData;

/**
 * Created by dmitriy on 30.08.15.
 */
public class WeatherDataSpiceService extends SpringAndroidSpiceService {

    private static final int WEBSERVICES_TIMEOUT = 10000;

    @Override
    public RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout( WEBSERVICES_TIMEOUT );
        httpRequestFactory.setConnectTimeout( WEBSERVICES_TIMEOUT );
        restTemplate.setRequestFactory( httpRequestFactory );

        SimpleXmlHttpMessageConverter simpleXmlHttpMessageConverter = new SimpleXmlHttpMessageConverter();
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        final List<HttpMessageConverter< ? >> listHttpMessageConverters = restTemplate.getMessageConverters();

        listHttpMessageConverters.add( simpleXmlHttpMessageConverter );
        listHttpMessageConverters.add(formHttpMessageConverter);
        listHttpMessageConverters.add(stringHttpMessageConverter);
        restTemplate.setMessageConverters(listHttpMessageConverters);

        return restTemplate;
    }

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        CacheManager cacheManager = new CacheManager();
        cacheManager.addPersister(new com.octo.android.robospice.persistence.springandroid.xml.SimpleSerializerObjectPersisterFactory(application));
        return cacheManager;
    }
}
