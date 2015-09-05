package ru.swdmitriy.forecastforkirov.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import ru.swdmitriy.forecastforkirov.logger.ForecastLogger;
import ru.swdmitriy.forecastforkirov.model.Time;
import ru.swdmitriy.forecastforkirov.model.WeatherData;

/**
 * Created by dmitriy on 05.09.15.
 */
public class ForecastDbHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "Forecast.db";
    private static final int DATABASE_VERSION = 1;

    public ForecastDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, WeatherData.class);
            TableUtils.createTable(connectionSource, Time.class);
            Log.d(ForecastLogger.TAG, "Created database");
        } catch (SQLException e) {
            Log.d(ForecastLogger.TAG, "Can't create database");
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, WeatherData.class, true);
            TableUtils.dropTable(connectionSource, Time.class, true);
            Log.d(ForecastLogger.TAG, "Updated database");
            // after we drop the old databases, we create the new ones
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.d(ForecastLogger.TAG, "Can't update database");
            throw new RuntimeException(e);
        }
    }
}
