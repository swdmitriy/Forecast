package ru.swdmitriy.forecastforkirov.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.Collection;

/**
 * Created by dmitriy on 28.08.15.
 */
@DatabaseTable(tableName = "weather_data")
@Root(name = "weatherdata", strict=false)
public class WeatherData {

    public Collection<Time> getTime() {
        return time;
    }

    public void setTime(Collection<Time> time) {
        this.time = time;
    }
    @Path("forecast/tabular")
    @ForeignCollectionField(eager = true)
    @ElementList(inline = true, required=false)
    private Collection<Time> time;

   /* public String getSize() {
        return String.valueOf(time.size());
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
