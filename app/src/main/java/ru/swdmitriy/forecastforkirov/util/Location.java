package ru.swdmitriy.forecastforkirov.util;

/**
 * Created by dmitriy on 08.09.15.
 */
public class Location {

    private static final String country = "Russland";
    private static final String region = "Kirov";
    private static final String city = "Kirov";

    private static final String lat = "58.6034";
    private static final String lon = "49.6672";

    public static String getCountry() {
        return country;
    }

    public static String getRegion() {
        return region;
    }

    public static String getCity() {
        return city;
    }
}
