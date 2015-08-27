package ru.swdmitriy.forecastforkirov.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import ru.swdmitriy.forecastforkirov.R;
import ru.swdmitriy.forecastforkirov.fragment.CurrentFragment;
import ru.swdmitriy.forecastforkirov.fragment.ForecastFragment;
import ru.swdmitriy.forecastforkirov.logger.ForecastLogger;

/**
 * Created by dmitriy on 28.07.15.
 */
public class ForecastActivity extends Activity {

    private CurrentFragment currentFragment;
    private ForecastFragment forecastFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        Log.d(ForecastLogger.TAG, "MainActivity onCreate()");
        manager = getFragmentManager();
        currentFragment = new CurrentFragment();
        forecastFragment = new ForecastFragment();
        transaction = manager.beginTransaction();
        transaction.add(R.id.forecast_container, currentFragment, CurrentFragment.TAG);
        transaction.commit();

    }


    public void onBtnClick(View v){
        Log.d(ForecastLogger.TAG, "onBtnClick()");
        transaction = manager.beginTransaction();
        switch(v.getId()){

            case R.id.btn_current:
                Log.d(ForecastLogger.TAG, "Go to CurrentFragment");
                if (manager.findFragmentByTag(CurrentFragment.TAG)==null){
                    if (manager.findFragmentByTag(ForecastFragment.TAG)!=null){
                        transaction.replace(R.id.forecast_container, currentFragment, CurrentFragment.TAG);
                    } else{
                        transaction.add(R.id.forecast_container, currentFragment, CurrentFragment.TAG);
                    }

                }
                break;
            case R.id.btn_forecast:
                Log.d(ForecastLogger.TAG, "Go to ForecastFragment");
                if (manager.findFragmentByTag(ForecastFragment.TAG) == null) {
                    if (manager.findFragmentByTag(CurrentFragment.TAG)!=null){
                        transaction.replace(R.id.forecast_container, forecastFragment, ForecastFragment.TAG);
                    } else{
                        transaction.add(R.id.forecast_container, forecastFragment, ForecastFragment.TAG);
                    }
                }
                break;
        }
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(ForecastLogger.TAG, "MainActivity onStart()");
    }


}
