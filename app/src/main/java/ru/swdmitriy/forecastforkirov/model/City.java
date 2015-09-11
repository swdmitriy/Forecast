package ru.swdmitriy.forecastforkirov.model;

import java.util.ArrayList;

/**
 * Created by dmitriy on 11.09.15.
 */
public class City {
    private String name;
    private String url;
    private String region;
    private String country_code;

    public City(String name, String url, String region, String country_code) {
        this.name = name;
        this.url = url;
        this.region = region;
        this.country_code = country_code;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry_code() {
        return country_code;
    }


}
