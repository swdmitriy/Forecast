package ru.swdmitriy.forecastforkirov.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ru.swdmitriy.forecastforkirov.logger.ForecastLogger;

/**
 * Created by dmitriy on 06.09.15.
 */
public class ForecastDbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "Forecast.db";
    private static final int DATABASE_VERSION = 1;

    public static final String WEATHERDATA_TABLENAME = "weather_data";
    public static final String TIME_TABLENAME = "time";

    public static final String WEATHERDATA_ID = "id";
    public static final String WEATHERDATA_LASTUPDATE = "last_update";
    public static final String TIME_ID = "id";
    public static final String TIME_FROM = "time_from";
    public static final String TIME_TO = "time_to";
    public static final String TIME_PRECIPITATION = "precipitation";
    public static final String TIME_TEMPERATURE = "temperature";
    public static final String TIME_PHENOMENON = "phenomenon";
    public static final String TIME_WEATHERDATAID = "weather_id";




    public ForecastDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(ForecastLogger.TAG, "Create db");
        db.execSQL("CREATE TABLE " + WEATHERDATA_TABLENAME + "(`" + WEATHERDATA_LASTUPDATE + "` VARCHAR , " + WEATHERDATA_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT );");
        db.execSQL("CREATE TABLE " + TIME_TABLENAME +" (`" + TIME_FROM + "` VARCHAR , `"
                +TIME_WEATHERDATAID+"` INTEGER , `"+TIME_TO+"` VARCHAR , `" + TIME_PRECIPITATION
                + "` DOUBLE , `" + TIME_TEMPERATURE + "` DOUBLE , `"
                + TIME_PHENOMENON + "` INTEGER , `" + TIME_ID
                + "` INTEGER PRIMARY KEY AUTOINCREMENT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
