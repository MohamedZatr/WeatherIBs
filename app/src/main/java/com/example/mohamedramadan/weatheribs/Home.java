package com.example.mohamedramadan.weatheribs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    /**
     *
     * @param view
     *
     * this is on clock function to go to Map Activity
     */
    public void goTOMap(View view) {
        startActivity(new Intent(this,MapsActivity.class));
    }

    /**
     *
     * @param view
     *
     * this is on clock function to go to Cities Activity
     */
    public void cities(View view) {
        startActivity(new Intent(this,Cities.class));
    }
}
