package ru.swdmitriy.forecastforkirov;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by dmitriy on 28.07.15.
 */
public class MainActivity extends Activity{
    private MyTask mt;
    private TextView tempView;
    private TextView timeStampView;
    private static final String POST_URL = "http://pogoda.kirov.ru/php/get_cur_weather_informer.php";
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String POST_PARAMS = "station=27199";
    private static final String POST_REFERER = "http://pogoda.kirov.ru/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        tempView = (TextView)findViewById(R.id.tempView);
        timeStampView = (TextView)findViewById(R.id.timeStampView);
    }

    public void Refresh(View v){

        mt = new MyTask();
        mt.execute();
    }

    class MyTask extends AsyncTask<Void, Void, String> {

        protected ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            progressDialog = ProgressDialog.show(MainActivity.this, "Ожидание...", "Подождите, погода обновляется");

        }

        @Override
        protected String doInBackground(Void... params)  {
            try {
                URL obj = new URL(POST_URL);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", USER_AGENT);
                con.setRequestProperty("Referer", POST_REFERER);

                // For POST only - START
                con.setDoOutput(true);
                OutputStream os = con.getOutputStream();
                os.write(POST_PARAMS.getBytes());
                os.flush();
                os.close();
                // For POST only - END

                int responseCode = con.getResponseCode();
                System.out.println("POST Response Code :: " + responseCode);

                if (responseCode == HttpURLConnection.HTTP_OK) { //success
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    System.out.println(response.toString());
                    return response.toString();
                } else {
                    System.out.println("POST request not worked");
                    return "Error 404";
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            JSONObject forecastObject, tempObject;
            String temperature;
            String[] timeStamp;
            try {
                forecastObject = new JSONObject(result);
                tempObject = forecastObject.getJSONObject("t_air");
                temperature = tempObject.getString("v");
                timeStamp = tempObject.getString("tm").split(" ");


                tempView.setText(temperature);
                timeStampView.setText(new String(timeStamp[0].getBytes("UTF-8"), "UTF-8") + " " + timeStamp[1]);
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                tempView.setText("Error 404");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                tempView.setText("Error 404");
            }


        }
    }
}
