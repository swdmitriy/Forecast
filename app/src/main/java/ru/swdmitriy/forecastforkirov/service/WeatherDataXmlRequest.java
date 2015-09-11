package ru.swdmitriy.forecastforkirov.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import org.springframework.web.client.RestClientException;

import java.util.Collection;

import ru.swdmitriy.forecastforkirov.db.ForecastDbHelper;
import ru.swdmitriy.forecastforkirov.logger.ForecastLogger;
import ru.swdmitriy.forecastforkirov.model.Time;
import ru.swdmitriy.forecastforkirov.model.WeatherData;

/**
 * Created by dmitriy on 30.08.15.
 */
public class WeatherDataXmlRequest extends SpringAndroidSpiceRequest<WeatherData> {

    private String baseUrl;
    Context context;

    public WeatherDataXmlRequest(Context context, String country, String region, String city) {
        super(WeatherData.class);
        this.context = context;
        this.baseUrl = String.format( "http://www.yr.no/place/%s/%s/%s/forecast.xml",country, region, city);
    }

    @Override
    public WeatherData loadDataFromNetwork() throws RestClientException {
        Log.d(ForecastLogger.TAG,"Call web service " + baseUrl);
        WeatherData result = getRestTemplate().getForObject(baseUrl, WeatherData.class);
        saveResult(result);
        return result;
    }

    private void saveResult(WeatherData result){
        ForecastDbHelper fdbh = ForecastDbHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = fdbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(fdbh.WEATHERDATA_LASTUPDATE, result.getLastupdate());
        long weatherId = sqLiteDatabase.insert(fdbh.WEATHERDATA_TABLENAME, null, cv);
        Log.d(ForecastLogger.TAG, "SQL Insert weatherdata "+weatherId);
        Collection<Time> times = result.getTimes();
        for (Time time:times){
            cv = new ContentValues();
            cv.put(fdbh.TIME_WEATHERDATAID, weatherId);
            cv.put(fdbh.TIME_FROM, time.getFrom());
            cv.put(fdbh.TIME_TO, time.getTo());
            cv.put(fdbh.TIME_PHENOMENON, time.getPhenomenon());
            cv.put(fdbh.TIME_PRECIPITATION, time.getPrecipitation());
            cv.put(fdbh.TIME_TEMPERATURE, time.getTemperature());
            sqLiteDatabase.insert(fdbh.TIME_TABLENAME, null, cv);

            Log.d(ForecastLogger.TAG, "SQL Insert time");
        }
        sqLiteDatabase.close();
        fdbh.close();

    }
}
