package com.example.mohamedramadan.weatheribs;

/**
 * Created by Mohamed Ramadan on 26-May-18.
 */

public class Weatherdicrip {
   private String main, description,icon ;

    /**
     * get the main description of weather
     * @return
     */
    public String getMain() {
        return main;
    }

    /**
     * set the main description of weather
     * @param main
     */

    public void setMain(String main) {
        this.main = main;
    }

    /**
     * get the  description of weather
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * set the  description of weather
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get the icon description of weather
     * @return
     */
    public String getIcon() {
        return icon;
    }

    /**
     * set the icon description of weather
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
}
