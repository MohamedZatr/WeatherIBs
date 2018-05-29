package com.example.mohamedramadan.weatheribs;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Mohamed Ramadan on 25-May-18.
 */

public class MyAdapter extends RecyclerView.Adapter<Holder> {

    Context context;
    List<CityWeather> list;

    public MyAdapter(Context context, List<CityWeather> cityWeathers) {
        this.context = context;
        this.list = cityWeathers;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.listitem,null,false);
       Holder holder = new Holder(view , context);
       return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        CityWeather cityWeather =  list.get(position);
        holder.city.setText(cityWeather.getCityName());
        holder.temp.setText("Temp:- "+cityWeather.convertToCelsius(cityWeather.getCurrentTemp())+"°C");
        holder.humidity.setText("Humidity:- "+cityWeather.getHumidity()+"%");
        holder.windSpeed.setText("Wind Speed:- "+cityWeather.getWindSpeed()+"m/s");
        holder.windDegree.setText("Wind Degree:- "+cityWeather.getWindDegree()+"°");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

// this method refresh my adapter
    public void filterList(List<CityWeather> weathers) {
        list = weathers;
        notifyDataSetChanged();
    }
}

class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
TextView city, temp, humidity, windSpeed, windDegree;
    Context context;
    public Holder(View itemView,Context context) {
        super(itemView);
        this.context = context;
        itemView.setOnClickListener(this);
        city = itemView.findViewById(R.id.city);
        temp = itemView.findViewById(R.id.temp);
        humidity = itemView.findViewById(R.id.humidity);
        windSpeed = itemView.findViewById(R.id.wind_Speed);
        windDegree = itemView.findViewById(R.id.wind_degree);

    }
    @Override
    public void onClick(View v) {
        TextView textView = v.findViewById(R.id.city);
        Intent intent = new Intent(context,CityDetails.class);
        intent.putExtra("CityName",textView.getText().toString());
        context.startActivity(intent);
    }
}
