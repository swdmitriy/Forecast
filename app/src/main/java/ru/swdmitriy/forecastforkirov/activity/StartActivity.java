package ru.swdmitriy.forecastforkirov.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;

import ru.swdmitriy.forecastforkirov.R;
import ru.swdmitriy.forecastforkirov.adapter.CityAdapter;
import ru.swdmitriy.forecastforkirov.model.Cities;
import ru.swdmitriy.forecastforkirov.model.City;
import ru.swdmitriy.forecastforkirov.service.CitySearchRequest;
import ru.swdmitriy.forecastforkirov.service.CitySearchService;

/**
 * Created by dmitriy on 11.09.15.
 */
public class StartActivity extends Activity{
    AutoCompleteTextView citySearch;
    private SpiceManager spiceManager = new SpiceManager(CitySearchService.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        final ArrayList<City> cities = new ArrayList<City>();
        cities.add(new City("Kirov", " ","Regional capital (Russia)", ""));
        cities.add(new City("Moscow", "", "Country capital", ""));
        final CityAdapter cityAdapter = new CityAdapter(this, R.layout.item_city, cities);
        cityAdapter.setNotifyOnChange(true);
        citySearch = (AutoCompleteTextView) findViewById(R.id.citySearch);
        citySearch.setAdapter(cityAdapter);
        citySearch.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            {


                cityAdapter.addAll(cities);
                cityAdapter.notifyDataSetChanged();
                //autoCompleteAdapter.clear();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }
        });

        CitySearchRequest request = new CitySearchRequest("Kirov");
        spiceManager.execute(request, new CitySearchRequestListener());


    }
    private final class CitySearchRequestListener implements RequestListener<String> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(StartActivity.this, "Error: " + spiceException.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(String result) {
            Toast.makeText(StartActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        spiceManager.shouldStop();
    }

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }
}
