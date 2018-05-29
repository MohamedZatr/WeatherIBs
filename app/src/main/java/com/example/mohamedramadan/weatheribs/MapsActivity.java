package com.example.mohamedramadan.weatheribs;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    // ot save data of cities
    List<CityWeather> cityWeathers = new ArrayList<>();
    // to save id of marker
    List<String> markerid = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // get data of cities From class CityWeather
        cityWeathers = CityWeather.getWeathers();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        //to set replace default marker by icon
        IconGenerator iconGenerator = new IconGenerator(this);
        iconGenerator.setTextAppearance(R.style.iconGenText);
        iconGenerator.setStyle(IconGenerator.STYLE_PURPLE);
        // for loop to add  a marker in all cities
        for (int pos = 0; pos < cityWeathers.size(); pos++) {
            Bitmap bitmap = iconGenerator.makeIcon(cityWeathers.get(pos).getCityName() +" "+cityWeathers.get(pos).convertToCelsius(cityWeathers.get(pos).getCurrentTemp())+"°C");
            //get Latitude
            double v = cityWeathers.get(pos).getLatitude();
            //get Latitude
            double v1 = cityWeathers.get(pos).getLonitude();
            LatLng City = new LatLng( v, v1);
            // add Marker of city
            Marker marker = mMap.addMarker(new MarkerOptions().position(City).icon(BitmapDescriptorFactory.fromBitmap(bitmap)));
            markerid.add(marker.getId());
            mMap.moveCamera(CameraUpdateFactory.newLatLng(City));
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    // get the index of marker is clicked
                    int citydata = markerid.indexOf(marker.getId());

                    Geocoder gcd = new Geocoder(MapsActivity.this, Locale.getDefault());
                    LatLng postion = marker.getPosition();
                    List<Address> addresses = null;
                    String s =  "";
                    try {
                        addresses = gcd.getFromLocation(postion.latitude, postion.longitude, 1);

                        if (addresses.size() > 0) {
                            s+="Country:-          " + addresses.get(0).getCountryName()+"\n";
                            s+="CountryCode:- " + addresses.get(0).getCountryCode()+"\n";
                            s+="AdminArea:-   "+addresses.get(0).getAdminArea()+"\n";
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    String data = "";
                    data +=s + "City:-                 "+cityWeathers.get(citydata).getCityName()+"\n"+
                            "Temp:-              "+cityWeathers.get(citydata).convertToCelsius(cityWeathers.get(citydata).getCurrentTemp())+"°C\n"+
                            "Humidity:-        "+cityWeathers.get(citydata).getHumidity()+"%\n"+
                            "Wind Speed:-   "+cityWeathers.get(citydata).getWindSpeed()+"m/s\n"+
                            "Wind Degree:-  "+cityWeathers.get(citydata).getWindDegree()+"°\n";

                    // pop dialog of city details
                    new AlertDialog.Builder(MapsActivity.this)
                            .setMessage(data).show();
                    return true;
                }
            });
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(cityWeathers.get(cityWeathers.size()/2).getLatitude(),cityWeathers.get(cityWeathers.size()/2).getLonitude()),6.0f));
    }


}
