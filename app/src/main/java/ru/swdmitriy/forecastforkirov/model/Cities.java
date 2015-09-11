package ru.swdmitriy.forecastforkirov.model;

import java.util.ArrayList;

/**
 * Created by dmitriy on 11.09.15.
 */
public class Cities {

    private ArrayList<City> cities;

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public Cities(ArrayList<City> cities) {
        this.cities = cities;
    }
}
