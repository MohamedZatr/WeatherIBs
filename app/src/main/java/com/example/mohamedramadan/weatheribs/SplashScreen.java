package com.example.mohamedramadan.weatheribs;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SplashScreen extends AppCompatActivity {
    private ProgressBar progressBar;
    // list of CityWeather To save data
    List<CityWeather> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        progressBar = findViewById(R.id.progressbar);
        new  Thread(){
            @Override
            public void run() {
                dowork();
                startApp();
                finish();
            }
        }.start();
        // get all cities from file Strings
        String[] strings = getResources().getStringArray(R.array.cities);
        list = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            dataWeather dataWeather = new dataWeather();
            try {
                CityWeather cityWeather = new CityWeather();
                //get String data and parse
                JSONObject object = new JSONObject(dataWeather.execute(strings[i]).get());
                cityWeather.setLonitude(object.getJSONObject("coord").getDouble("lon"));
                cityWeather.setLatitude(object.getJSONObject("coord").getDouble("lat"));
                cityWeather.setCityName(object.getString("name"));
                cityWeather.setCurrentTemp(String.valueOf(object.getJSONObject("main").getDouble("temp")));
                cityWeather.setPressure(String.valueOf(object.getJSONObject("main").getInt("pressure")));
                cityWeather.setHumidity(String.valueOf(object.getJSONObject("main").getInt("humidity")));
                cityWeather.setMinTemp(String.valueOf(object.getJSONObject("main").getDouble("temp_min")));
                cityWeather.setMaxTemp(String.valueOf(object.getJSONObject("main").getDouble("temp_max")));
                cityWeather.setWindSpeed(String.valueOf(object.getJSONObject("wind").getDouble("speed")));
                cityWeather.setWindDegree(String.valueOf(object.getJSONObject("wind").getDouble("deg")));
                JSONArray jsonArray =  object.getJSONArray("weather");
                List<Weatherdicrip> weatherdicrips = new ArrayList();
                // this fir loop to save Weather description
                for(int pos = 0; pos < jsonArray.length();pos++)
                {
                    Weatherdicrip weatherdicrip = new Weatherdicrip();
                    weatherdicrip.setMain(jsonArray.getJSONObject(pos).getString("main"));
                    weatherdicrip.setDescription(jsonArray.getJSONObject(pos).getString("description"));
                    weatherdicrip.setIcon(jsonArray.getJSONObject(pos).getString("icon"));
                    weatherdicrips.add(weatherdicrip);
                }
                cityWeather.setWeatherdicrip(weatherdicrips);
                // this list to add all data of my cities
                list.add(cityWeather);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            CityWeather.setWeathers(list);
        }



    }

    private void startApp() {
        progressBar.animate().alpha(0f).setDuration(1000);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        startActivity(new Intent(SplashScreen.this,Home.class));
    }

    private void dowork() {
        for (int progressval = 0; progressval<110; progressval+=10)
        {
            try {
                Thread.sleep(500);
                progressBar.setProgress(progressval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class dataWeather extends AsyncTask<String,Void,String> {
        StringBuffer stringBuffer;
        @Override
        protected String doInBackground(String... strings) {
            // url contains the data
            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + strings[0] + "&APPID=8072ed7ede0fc9bcb8591a2a2eb90cfd";
            try {
                URL url1 = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                int data = reader.read();
                stringBuffer = new StringBuffer();
                while (data != -1) {
                    char current = (char) data;
                    data = reader.read();
                    stringBuffer.append(current);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuffer.toString();
        }
    }
}
