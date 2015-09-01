package ru.swdmitriy.forecastforkirov.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by dmitriy on 28.08.15.
 */
@DatabaseTable(tableName = "weather_data")
@Root(name = "weatherdata", strict=false)
public class WeatherData {

    public Collection<Time> getTimes() {
        return times;
    }

    public void setTimes(Collection<Time> times) {
        this.times = times;
    }
    @Path("forecast/tabular")
    @ForeignCollectionField(eager = true)
    @ElementList(name="time", inline = true)
    private Collection<Time> times;

   /* public String getSize() {
        return String.valueOf(times.size());
    }*/

    @DatabaseField(generatedId = true)
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WeatherData() {
    }
}
