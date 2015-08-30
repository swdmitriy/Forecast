package ru.swdmitriy.forecastforkirov.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ru.swdmitriy.forecastforkirov.R;
import ru.swdmitriy.forecastforkirov.model.Time;
import ru.swdmitriy.forecastforkirov.model.WeatherData;

/**
 * Created by dmitriy on 30.08.15.
 */
public class ForecastAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Time> objects;


    public ForecastAdapter(Context context, ArrayList<Time> objects) {
        ctx = context;
        this.objects = objects;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item_forecast, parent, false);
        }

        Time time = getProduct(position);
        String from = time.getFrom();
        String to = time.getTo();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date dateFrom = new Date();
        Date dateTo = new Date();
        try {
            dateFrom = formatter.parse(from);
            dateTo = formatter.parse(to);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter = new SimpleDateFormat("dd.MM");
        String dateString = formatter.format(dateFrom);

        formatter = new SimpleDateFormat("HH");
        StringBuilder timeString = new StringBuilder();
        timeString.append(formatter.format(dateFrom));
        timeString.append(" - ");
        timeString.append(formatter.format(dateTo));

        ((TextView) view.findViewById(R.id.itemFrom)).setText(dateString);
        ((TextView) view.findViewById(R.id.itemTo)).setText(timeString.toString());
        ((TextView) view.findViewById(R.id.itemTemp)).setText(new String().valueOf((time.getTemperature()!=null?time.getTemperature() : "-")));
        ((TextView) view.findViewById(R.id.itemMinTemp)).setText(new String().valueOf((time.getMinTemperature()!=null?time.getMinTemperature():"-")));
        ((TextView) view.findViewById(R.id.itemMaxTemp)).setText(new String().valueOf((time.getMaxTemperature()!=null?time.getMaxTemperature():"-")));
        ((TextView) view.findViewById(R.id.itemPrecipitation)).setText(new String().valueOf((time.getPrecipitation()!=null?time.getPrecipitation():"-")));

        return view;
    }

    Time getProduct(int position) {
        return ((Time) getItem(position));
    }

}
