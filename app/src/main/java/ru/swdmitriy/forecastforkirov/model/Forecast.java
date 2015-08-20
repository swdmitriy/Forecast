package ru.swdmitriy.forecastforkirov.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dmitriy on 20.08.15.
 */

@SuppressWarnings("serial")
public class Forecast {
    @SerializedName("t_air")
    private ForecastValue temperature;
    private ForecastValue humidity;
    private ForecastValue pressure;
    private ForecastValue windSpeed;
    private ForecastValue phenomenon;

    public ForecastValue getTemperature() {
        return temperature;
    }

    public void setTemperature(ForecastValue temperature) {
        this.temperature = temperature;
    }
}
