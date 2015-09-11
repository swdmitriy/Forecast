package ru.swdmitriy.forecastforkirov.service;

import android.net.Uri;

import com.octo.android.robospice.request.okhttp.OkHttpSpiceRequest;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import ru.swdmitriy.forecastforkirov.model.Cities;
import ru.swdmitriy.forecastforkirov.model.City;

/**
 * Created by dmitriy on 11.09.15.
 */
public class CitySearchRequest extends OkHttpSpiceRequest<String> {
    private String request;
    public CitySearchRequest(String request) {
        super(String.class);
        this.request = request;
    }

    @Override
    public String loadDataFromNetwork() throws Exception {
        String result;
        Uri.Builder uriBuilder = Uri.parse("http://www.yr.no/_/settspr.aspx?spr=eng&redir=%2f_%2fwebsvc%2fjsonforslagsboks.aspx?s="+request).buildUpon();
        //uriBuilder.appendQueryParameter("s", request);

        URI uri = new URI(uriBuilder.build().toString());

        HttpURLConnection connection = getOkHttpClient(). open(uri.toURL());
        InputStream in = null;
        try {
            // Read the response.
            in = connection.getInputStream();
            result = IOUtils.toString(in, "UTF-8");
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return result;
    }

    private List<City> parseCity(String in){

        return null;
    }

}
