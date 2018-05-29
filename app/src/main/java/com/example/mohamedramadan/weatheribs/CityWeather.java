package com.example.mohamedramadan.weatheribs;

import java.util.List;

/**
 * Created by Mohamed Ramadan on 24-May-18.
 */

public class CityWeather {
    private String cityName, currentTemp, humidity, windSpeed,
            windDegree, minTemp, maxTemp, pressure;
    private double lonitude, latitude;
    private List<Weatherdicrip> weatherdicrip;
    private static List<CityWeather> weathers;

    /**
     * get the Weathers Description of city
     * @return
     */
    public List<Weatherdicrip> getWeatherdicrip() {
        return weatherdicrip;
    }

    /**
     * set the Weathers Description of city
     * @param weatherdicrip
     */
    public void setWeatherdicrip(List<Weatherdicrip> weatherdicrip) {
        this.weatherdicrip = weatherdicrip;
    }

    /**
     * get the list of of cities Weather
     * @return
     */
    public static List<CityWeather> getWeathers() {
        return weathers;
    }

    /**
     * set the list of of cities Weather
     * @param weathers
     */
    public static void setWeathers(List<CityWeather> weathers) {
        CityWeather.weathers = weathers;
    }


    /**
     * get the CityName of city
     * @return
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * set the CityName of city
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * get the CurrentTemp of city
     * @return
     */
    public String getCurrentTemp() {
        return currentTemp;
    }

    /**
     * set the CurrentTemp of city
     * @param currentTemp
     */
    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    /**
     * get the Humidity of city
     * @return
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     * set the Humidity of city
     * @param humidity
     */
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    /**
     * get the WindSpeed of city
     * @return
     */
    public String getWindSpeed() {
        return windSpeed;
    }

    /**
     * set the WindSpeed of city
     * @param windSpeed
     */
    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * get the WindDegree of city
     * @return
     */
    public String getWindDegree() {
        return windDegree;
    }

    /**
     * set the WindDegree of city
     * @param windDegree
     */
    public void setWindDegree(String windDegree) {
        this.windDegree = windDegree;
    }

    /**
     * get the minTemp of city
     * @return
     */
    public String getMinTemp() {
        return minTemp;
    }

    /**
     * set the minTemp of city
     * @param minTemp
     */
    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    /**
     * get the maxTemp of city
     * @return
     */
    public String getMaxTemp() {
        return maxTemp;
    }

    /**
     * set the maxTemp of city
     * @param maxTemp
     */
    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    /**
     * get the pressure of city
     * @return
     */
    public String getPressure() {
        return pressure;
    }

    /**
     * set the pressure of city
     * @param pressure
     */
    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    /**
     * get the lonitude of city
     * @return
     */
    public double getLonitude() {
        return lonitude;
    }

    /**
     * set the lonitude of city
     * @param lonitude
     */
    public void setLonitude(double lonitude) {
        this.lonitude = lonitude;
    }

    /**
     * get the Latitude of city
     * @return
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     *  set the Latitude of city
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @param temp
     * @return celsius Temp
     * the method convert from kelvin to celsius
     */
    public String convertToCelsius(String temp) {
        return String.format("%.2f", Double.parseDouble(temp) - 273.15);
    }

}
