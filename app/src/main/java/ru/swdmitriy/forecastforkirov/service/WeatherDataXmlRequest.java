package ru.swdmitriy.forecastforkirov.service;

import android.util.Log;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import org.springframework.web.client.RestClientException;

import ru.swdmitriy.forecastforkirov.logger.ForecastLogger;
import ru.swdmitriy.forecastforkirov.model.WeatherData;

/**
 * Created by dmitriy on 30.08.15.
 */
public class WeatherDataXmlRequest extends SpringAndroidSpiceRequest<WeatherData> {

    private String baseUrl;

    public WeatherDataXmlRequest(String lat, String lon) {
        super(WeatherData.class);
        this.baseUrl = String.format( "http://api.yr.no/weatherapi/locationforecastlts/1.2/?lat=%s&lon=%s", lat, lon);
    }

    @Override
    public WeatherData loadDataFromNetwork() throws RestClientException {
        Log.d(ForecastLogger.TAG,"Call web service " + baseUrl);
        return getRestTemplate().getForObject( baseUrl, WeatherData.class );
    }
}
