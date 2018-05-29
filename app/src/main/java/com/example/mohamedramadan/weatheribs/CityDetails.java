package com.example.mohamedramadan.weatheribs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class CityDetails extends AppCompatActivity {
    // this TextView to Display data
    TextView city, main, disciption, temp, maxTemp, minTemp,
            humidity, windspeed, winddgree, pressure;
    ImageView imageView;
    // to get city is selected
    CityWeather weather = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);

        city = findViewById(R.id.city_city);
        main = findViewById(R.id.city_main_weather);
        disciption = findViewById(R.id.city_descripton);
        temp = findViewById(R.id.city_temp);
        maxTemp = findViewById(R.id.city_max_temp);
        minTemp = findViewById(R.id.city_min_temp);
        humidity = findViewById(R.id.city_humidity);
        winddgree = findViewById(R.id.city_wind_degree);
        windspeed = findViewById(R.id.city_wind_speed);
        pressure = findViewById(R.id.city_pressure);
        Intent intent = getIntent();
        // get the name of city of item selected
        String cityname = intent.getStringExtra("CityName");

        if (cityname != null) {
            // this for loop to get city is selected
            for (int pos = 0; pos < CityWeather.getWeathers().size(); pos++) {
                if (CityWeather.getWeathers().get(pos).getCityName().equals(cityname)) {
                    weather = CityWeather.getWeathers().get(pos);
                    return;
                }
            }
            city.setText(weather.getCityName());
            main.setText(weather.getWeatherdicrip().get(0).getMain());
            disciption.setText(weather.getWeatherdicrip().get(0).getDescription());
            temp.setText(weather.convertToCelsius(weather.getCurrentTemp()) + "°C");
            maxTemp.setText(weather.convertToCelsius(weather.getMaxTemp()) + "°C");
            minTemp.setText(weather.convertToCelsius(weather.getMinTemp()) + "°C");
            humidity.setText(weather.getHumidity() + "%");
            windspeed.setText(weather.getWindSpeed() + "m/s");
            winddgree.setText(weather.getWindDegree() + "°");
            pressure.setText(weather.getPressure() + "hPa");
            String url = "http://openweathermap.org/img/w/" + weather.getWeatherdicrip().get(0).getIcon()+".png";
            imageView = findViewById(R.id.descrip_image);
            Picasso.with(this).load(url).into(imageView);
        }
    }
}
