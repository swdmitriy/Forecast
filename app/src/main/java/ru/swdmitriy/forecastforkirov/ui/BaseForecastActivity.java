package ru.swdmitriy.forecastforkirov.ui;

import android.app.Activity;

import com.octo.android.robospice.SpiceManager;

import ru.swdmitriy.forecastforkirov.service.ForecastService;

/**
 * Created by dmitriy on 19.08.15.
 */
public class BaseForecastActivity extends Activity {
    private SpiceManager spiceManager = new SpiceManager(ForecastService.class);

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }
}
