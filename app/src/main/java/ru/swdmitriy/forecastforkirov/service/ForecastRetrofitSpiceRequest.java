package ru.swdmitriy.forecastforkirov.service;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import ru.swdmitriy.forecastforkirov.model.Forecast;

/**
 * Created by dmitriy on 20.08.15.
 */
public class ForecastRetrofitSpiceRequest extends RetrofitSpiceRequest<Forecast, PogodaKirov> {


    private String city;
    public ForecastRetrofitSpiceRequest(String city) {

        super(Forecast.class, PogodaKirov.class);
        this.city = city;
    }
    @Override
    public Forecast loadDataFromNetwork() throws Exception {
        return getService().forecast(city);
    }

}
