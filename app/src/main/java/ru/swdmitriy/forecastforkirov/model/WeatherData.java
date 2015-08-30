package ru.swdmitriy.forecastforkirov.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.Date;
import java.util.List;

/**
 * Created by dmitriy on 28.08.15.
 */
@Root(name = "weatherdata")
public class WeatherData {

    @Element
    private Product product;


    @Root(name = "product")
    public class Product{
        @ElementList
        private List<Time> times;

        @Root(name = "time")
        public class Time{
            @Attribute
            private Date from;
            @Attribute
            private Date to;

            @Path("location/minTemperature")
            @Attribute(name = "value")
            private Double minTemperature;

            @Path("location/maxTemperature")
            @Attribute(name = "value")
            private Double maxTemperature;

            @Path("location/temperature")
            @Attribute(name = "value")
            private Double temperature;

            @Path("location/symbol")
            @Attribute(name = "number")
            private int phenomenon;

            @Path("location/precipitation")
            @Attribute(name = "value")
            private int precipitation;

        }

    }

}
