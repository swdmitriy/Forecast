package ru.swdmitriy.forecastforkirov.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import ru.swdmitriy.forecastforkirov.R;
import ru.swdmitriy.forecastforkirov.dbhelper.ForecastDbHelper;
import ru.swdmitriy.forecastforkirov.fragment.CurrentFragment;
import ru.swdmitriy.forecastforkirov.fragment.ForecastFragment;
import ru.swdmitriy.forecastforkirov.fragment.ForecastFragment.ReturnEventListener;
import ru.swdmitriy.forecastforkirov.logger.ForecastLogger;

/**
 * Created by dmitriy on 28.07.15.
 */
public class ForecastActivity extends Activity implements ReturnEventListener{

    private CurrentFragment currentFragment;
    private ForecastFragment forecastFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ForecastDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        Log.d(ForecastLogger.TAG, "MainActivity onCreate()");
        manager = getFragmentManager();
        currentFragment = new CurrentFragment();
        forecastFragment = new ForecastFragment();
        transaction = manager.beginTransaction();
        if (savedInstanceState==null&&manager.findFragmentByTag(CurrentFragment.TAG)==null){
                Log.d(ForecastLogger.TAG, "1st add CurrentFragment");
                transaction.add(R.id.forecast_container, currentFragment, CurrentFragment.TAG);
            }
        transaction.commit();
        dbHelper = new ForecastDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

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
                        Log.d(ForecastLogger.TAG, "replace with CurrentFragment");
                    } else{
                        transaction.add(R.id.forecast_container, currentFragment, CurrentFragment.TAG);
                        Log.d(ForecastLogger.TAG, "add CurrentFragment");
                    }

                }
                break;
            case R.id.btn_forecast:
                Log.d(ForecastLogger.TAG, "Go to ForecastFragment");
                if (manager.findFragmentByTag(ForecastFragment.TAG) == null) {
                    if (manager.findFragmentByTag(CurrentFragment.TAG)!=null){
                        transaction.replace(R.id.forecast_container, forecastFragment, ForecastFragment.TAG);
                        Log.d(ForecastLogger.TAG, "replace with ForecastFragment");
                    } else{
                        transaction.add(R.id.forecast_container, forecastFragment, ForecastFragment.TAG);
                        Log.d(ForecastLogger.TAG, "add ForecastFragment");
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


    @Override
    public void returnEvent() {
        transaction = manager.beginTransaction();
        Log.d(ForecastLogger.TAG, "Go to CurrentFragment");
        if (manager.findFragmentByTag(CurrentFragment.TAG)==null){
            if (manager.findFragmentByTag(ForecastFragment.TAG)!=null){
                transaction.replace(R.id.forecast_container, currentFragment, CurrentFragment.TAG);
            } else{
                transaction.add(R.id.forecast_container, currentFragment, CurrentFragment.TAG);
            }

        }
        transaction.commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(ForecastLogger.TAG, "MainActivity onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(ForecastLogger.TAG, "MainActivity onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(ForecastLogger.TAG, "MainActivity onPause() ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(ForecastLogger.TAG, "MainActivity onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(ForecastLogger.TAG, "MainActivity onDestroy()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(ForecastLogger.TAG, "MainActivity onSaveInstanceState()");
        if (manager.findFragmentByTag(CurrentFragment.TAG)!=null){
            outState.putString("MyFragment", CurrentFragment.TAG);
        } else {
            if (manager.findFragmentByTag(ForecastFragment.TAG)!=null) {
                outState.putString("MyFragment", ForecastFragment.TAG);
            }
        }
    }
}
