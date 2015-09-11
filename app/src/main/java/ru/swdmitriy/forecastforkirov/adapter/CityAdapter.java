package ru.swdmitriy.forecastforkirov.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import ru.swdmitriy.forecastforkirov.R;
import ru.swdmitriy.forecastforkirov.model.City;

/**
 * Created by dmitriy on 11.09.15.
 */
public class CityAdapter extends ArrayAdapter<City> implements Filterable{
    private Context context;
    private LayoutInflater inflater;
    private List<City> objects;
    private int resource;

    public CityAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
        this.objects = objects;
    }


    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public City getItem(int position) {
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
            view = inflater.inflate(R.layout.item_city, parent, false);
        }
        City city = objects.get(position);
        TextView cityName = (TextView) view.findViewById(R.id.cityName);
        TextView cityRegion = (TextView) view.findViewById(R.id.cityRegion);
        cityName.setText(city.getName());
        cityRegion.setText(city.getRegion());
        return view;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {


                    // Assign the data to the FilterResults
                    filterResults.values = objects;
                    filterResults.count = objects.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    objects = (List<City>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }};
        return filter;
    }

}
