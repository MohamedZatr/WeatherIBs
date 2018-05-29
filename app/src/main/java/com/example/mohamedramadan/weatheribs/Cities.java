package com.example.mohamedramadan.weatheribs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class Cities extends AppCompatActivity implements TextWatcher {
    RecyclerView recyclerView;
    // to search by city
    EditText editText;

    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        recyclerView = findViewById(R.id.recycle);
        editText = findViewById(R.id.search);
        adapter = new MyAdapter(this, CityWeather.getWeathers());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        editText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        filter(s.toString());
    }
// this method get characters create new list of city startsWith this characters and send it to adapter
    private void filter(String s) {
        List<CityWeather> weathers = new ArrayList<>();
        for(CityWeather weather: CityWeather.getWeathers())
        {
            if(weather.getCityName().toLowerCase().startsWith(s.toLowerCase()))
            weathers.add(weather);
        }
        adapter.filterList(weathers);
    }
}
