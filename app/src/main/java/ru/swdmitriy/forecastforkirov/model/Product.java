package ru.swdmitriy.forecastforkirov.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.Date;
import java.util.List;

/**
 * Created by dmitriy on 30.08.15.
 */
@Root(name = "product", strict=false)
@DatabaseTable(tableName = "product")
public class Product {

    @DatabaseField(generatedId = true)
    private int id;

    @ForeignCollectionField(eager = false)
    private List<Time> times;

    public String getSize(){
        return String.valueOf(times.size());
    }

}