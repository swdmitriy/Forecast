package ru.swdmitriy.forecastforkirov.service;

import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;
import ru.swdmitriy.forecastforkirov.model.Forecast;

/**
 * Created by dmitriy on 20.08.15.
 */

public interface PogodaKirov {
    @FormUrlEncoded
    @Headers({
            "User-Agent: Mozilla/5.0",
            "Referer: http://pogoda.kirov.ru/"
    })
    @POST("/php/get_cur_weather_informer.php")
    Forecast forecast(@Field("station") String city);
}

