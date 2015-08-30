package ru.swdmitriy.forecastforkirov.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.Date;

/**
 * Created by dmitriy on 30.08.15.
 */
@Root(name = "time", strict=false)
@DatabaseTable(tableName = "time")
public class Time{
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "weather_id")
    private WeatherData weatherData;

    @Attribute
    @DatabaseField
    private String from;
    @Attribute
    @DatabaseField
    private String to;

    @Path("location/minTemperature")
    @Attribute(name = "value")
    @DatabaseField
    private Double minTemperature;

    @Path("location/maxTemperature")
    @Attribute(name = "value")
    @DatabaseField
    private Double maxTemperature;

    @Path("location/temperature")
    @Attribute(name = "value")
    @DatabaseField
    private Double temperature;

    @Path("location/symbol")
    @Attribute(name = "number")
    @DatabaseField
    private int phenomenon;

    @Path("location/precipitation")
    @DatabaseField
    @Attribute(name = "value")
    private Double precipitation;



    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Time() {
    }
}