package ru.swdmitriy.forecastforkirov.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dmitriy on 20.08.15.
 */
public class ForecastValue {

    @SerializedName("dt")
    private String date;
    @SerializedName("tm")
    private String time;
    @SerializedName("v")
    private String value;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
