package models;

//The Station Model is where most of my functions are run from as the data is easy to pass from inside this model

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;


@Entity
public class Station extends Model {           //Declaring the station model as well as creating the fields.
    public String name;
    public float latitude;
    public float longitude;


    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();       // Declaration of the reading Arraylist.

    public Station(String name, float latitude, float longitude) {   //Data is passed to this constructor via forms and a route.
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }    //A get function to return the name of the Station

    public int getLatestWeather() {                              //This function goes through the readings Arraylist and gets the most recent weather Code and returns it.
        int code;
        code = readings.get(readings.size() - 1).code;
        return code;
    }

    public float getTempCelsius() {                              //This function goes through the readings Arraylist and gets the most recent temperature and returns it.
        float tempC = 0;
        if (readings.size() > 0) {
            tempC = readings.get(readings.size() - 1).temperature;
        }
        return tempC;
    }

    public float getTempFarenheit() {                           //This function takes in the most recent temperature and then calculates it so that we can get the Farrenheit data.
        float tempF = 0;
        float tempC;
        if (readings.size() > 0) {
            tempC = readings.get(readings.size() - 1).temperature;
            tempF = (float) ((tempC * 1.8) + 32);

        }
        return tempF;
    }

    public double getWindChill() {                  /* The wind chill function gets temperature data and windspeed date, using that data we calculate the wind speed using the
                                                       equation on lin 71, we then take the decimal number and round it so that we get the data to however many decimals we write.
                                                    */
        float tempC;
        float windSpeed;
        double windChill = 0;
        double windChillr = 0;


        if (readings.size() > 0) {
            tempC = readings.get(readings.size() - 1).temperature;
            windSpeed = readings.get(readings.size() - 1).windSpeed;
            windChill = 13.12 + (0.6215 * tempC) - (11.37 * Math.pow(windSpeed, 0.16)) + 0.3965 * 6 * Math.pow(2, 0.16);
            windChillr = Math.round(windChill * 10.0) / 10.0;
        } else {

        }
        return windChillr;
    }

    public int getLatestPressure() {                         //This function goes through the readings Arraylist and gets the most recent pressure and returns it.
        int pressure = 0;
        if (readings.size() > 0) {
            pressure = readings.get(readings.size() - 1).pressure;
        }
        return pressure;
    }

    public int getBeaufortSpeed() {                                  /* The beaufort speed function goes through the arraylist and grabs the most recent windspeed, with that data
                                                                       it is checked to see where it falls within a range of values, depending on the value of the windspeed
                                                                       we can update the beaufort variable, at the end we return the beaufort value which we can use for display.
                                                                     */
        float windSpeed;
        int beaufort = 0;
        if (readings.size() > 0) {
            windSpeed = readings.get(readings.size() - 1).windSpeed;

            if (windSpeed >= 1 && windSpeed <= 5) {
                beaufort = 1;
            } else if (windSpeed >= 6 && windSpeed <= 11) {
                beaufort = 2;
            } else if (windSpeed >= 12 && windSpeed <= 19) {
                beaufort = 3;
            } else if (windSpeed >= 20 && windSpeed <= 28) {
                beaufort = 4;
            } else if (windSpeed >= 29 && windSpeed <= 38) {
                beaufort = 5;
            } else if (windSpeed >= 39 && windSpeed <= 49) {
                beaufort = 6;
            } else if (windSpeed >= 50 && windSpeed <= 61) {
                beaufort = 7;
            } else if (windSpeed >= 62 && windSpeed <= 74) {
                beaufort = 8;
            } else if (windSpeed >= 75 && windSpeed <= 88) {
                beaufort = 9;
            } else if (windSpeed >= 89 && windSpeed <= 102) {
                beaufort = 10;
            } else if (windSpeed >= 103 && windSpeed <= 117) {
                beaufort = 11;
            } else {
            }
        }
        return beaufort;
    }

    public String getWeather() {                                        /* This function is similar to the get beaufort function, we are taking in the most recent weather code from
                                                                           the readings Arraylist, we then use this code in multiple if statements, depending on the code
                                                                           we update the weather string and then return it, we can then use that string to display the weather type
                                                                           on our fomantic cards.
                                                                       */
        int weatherCode = 0;
        String weather = " ";
        if (readings.size() > 0) {
            weatherCode = readings.get(readings.size() - 1).code;

            if (weatherCode == 100) {
                weather += weather + " Clear ";
            } else if (weatherCode == 200) {
                weather += weather + " Partial clouds ";
            } else if (weatherCode == 300) {
                weather += weather + " Cloudy ";
            } else if (weatherCode == 400) {
                weather += weather + " Light Showers ";
            } else if (weatherCode == 500) {
                weather += weather + " Heavy Showers ";
            } else if (weatherCode == 600) {
                weather += weather + " Rain ";
            } else if (weatherCode == 700) {
                weather += weather + " Snow ";
            } else if (weatherCode == 800) {
                weather += weather + " Thunder ";
            } else {
                weather += weather + " Partial clouds ";
            }
        }
        return weather;
    }

    public String getWindDir() {                                        /* This function is similar to the get beaufort function, we are taking in the most recent wind direction
                                                                           that data is checked against a range of values and depending on the data we update the windDir String and
                                                                           return it so that we can display it on our Weather cards.
                                                                       */
        float windDire = 0;
        String windDir = " ";
        if (readings.size() > 0) {
            windDire = readings.get(readings.size() - 1).windDirection;

            if (windDire >= 348.75 || windDire <= 11.25) {
                windDir += windDir + " N ";
            } else if (windDire >= 11.25 && windDire <= 33.75) {
                windDir += windDir + " NNE ";
            } else if (windDire >= 33.75 && windDire <= 56.25) {
                windDir += windDir + " NE ";
            } else if (windDire >= 56.25 && windDire <= 78.75) {
                windDir += windDir + " ENE ";
            } else if (windDire >= 78.75 && windDire <= 101.25) {
                windDir += windDir + " E ";
            } else if (windDire >= 101.25 && windDire <= 123.75) {
                windDir += windDir + " ESE ";
            } else if (windDire >= 123.75 && windDire <= 146.25) {
                windDir += windDir + " SE ";
            } else if (windDire >= 146.25 && windDire <= 168.75) {
                windDir += windDir + " SSE ";
            } else if (windDire >= 168.75 && windDire <= 191.25) {
                windDir += windDir + " S ";
            } else if (windDire >= 191.25 && windDire <= 213.75) {
                windDir += windDir + " SSW ";
            } else if (windDire >= 213.75 && windDire <= 236.25) {
                windDir += windDir + " SW ";
            } else if (windDire >= 236.25 && windDire <= 258.75) {
                windDir += windDir + " WSW ";
            } else if (windDire >= 258.75 && windDire <= 281.25) {
                windDir += windDir + " W ";
            } else if (windDire >= 281.25 && windDire <= 303.75) {
                windDir += windDir + " WNW ";
            } else if (windDire >= 303.75 && windDire <= 326.25) {
                windDir += windDir + " NW ";
            } else if (windDire >= 326.25 && windDire <= 348.75) {
                windDir += windDir + " NNW ";
            } else {
            }
        }
        return windDir;
    }

    public String weatherIcon() {                                       /* This function is corresponds with the get weather function, the difference in this one is that we are utilising
                                                                           the wIcon String to display a fomantic icon on the card, the icon will match with the weather type.
                                                                       */
        int wCode = 0;
        String wIcon = " ";
        if (readings.size() > 0) {
            wCode = readings.get(readings.size() - 1).code;
            if (wCode == 100) {
                wIcon = "huge sun outline icon";
            } else if (wCode == 200) {
                wIcon = "huge cloud sun icon";
            } else if (wCode == 300) {
                wIcon = "huge cloud icon";
            } else if (wCode == 400) {
                wIcon = "huge cloud rain icon";
            } else if (wCode == 500) {
                wIcon = "huge cloud showers heavy icon";
            } else if (wCode == 600) {
                wIcon = "huge cloud showers heavy icon";
            } else if (wCode == 700) {
                wIcon = "huge snowflake icon";
            } else if (wCode == 800) {
                wIcon = "huge bolt icon";
            } else {
                wIcon = "huge cloud icon";
            }
        }
        return wIcon;
    }

    public String tempIcon() {                                           /* This function is similar to the above function, we are grabbing temperature data to update a string to
                                                                            display a temperature icon.
                                                                       */
        float tCode = 0;
        String tempIcon = " ";
        if (readings.size() > 0) {
            tCode = readings.get(readings.size() - 1).temperature;
            if (tCode >= 11) {
                tempIcon = "huge red temperature high icon";
            } else if (tCode <= 10.99) {
                tempIcon = "huge blue temperature low icon";
            } else {
                tempIcon = "huge temperature low icon";
            }
        }
        return tempIcon;
    }

    public String tempTrendIcon() {                                     /* This function is used to display a trend arrow on the weather cards, we use the trendIcon String to display
                                                                           the fomantic icon, we are checking the three most recent temperature reports and passing them into
                                                                           t1,t2 and t3, we are then checking to see if there are reports in a row that are trending up or down, and
                                                                           display an arrow in it's corresponding direction, if there is no up or down trend after 3 reading a neutral
                                                                           arrow will be shown instead to represent a steady weather pattern/
                                                                       */
        String trendIcon = " ";
        if (readings.size() >= 3) {
            float t1 = readings.get(readings.size() - 3).temperature;
            float t2 = readings.get(readings.size() - 2).temperature;
            float t3 = readings.get(readings.size() - 1).temperature;
            if (t3 > t2 && t2 > t1) {
                trendIcon += " huge long arrow alternate up icon ";
            } else if (t3 < t2 && t2 < t1) {
                trendIcon += " huge long arrow alternate down icon ";
            } else {
                trendIcon += " huge arrows alternate horizontal icon";
            }
        }
        return trendIcon;
    }

    public String windTrendIcon() {                                    //This function is the same as the above just pulling windSpeed instead.
        String trendIcon = " ";
        if (readings.size() >= 3) {
            float t1 = readings.get(readings.size() - 3).windSpeed;
            float t2 = readings.get(readings.size() - 2).windSpeed;
            float t3 = readings.get(readings.size() - 1).windSpeed;
            if (t3 > t2 && t2 > t1) {
                trendIcon += " huge long arrow alternate up icon ";
            } else if (t3 < t2 && t2 < t1) {
                trendIcon += " huge long arrow alternate down icon ";
            } else {
                trendIcon += " huge arrows alternate horizontal icon";
            }
        }
        return trendIcon;
    }

    public String pressureTrendIcon() {                               //This function is the same as the above just pulling pressure instead.
        String trendIcon = " ";
        if (readings.size() >= 3) {
            float t1 = readings.get(readings.size() - 3).pressure;
            float t2 = readings.get(readings.size() - 2).pressure;
            float t3 = readings.get(readings.size() - 1).pressure;
            if (t3 > t2 && t2 > t1) {
                trendIcon += " huge long arrow alternate up icon ";
            } else if (t3 < t2 && t2 < t1) {
                trendIcon += " huge long arrow alternate down icon ";
            } else {
                trendIcon += " huge arrows alternate horizontal icon";
            }
        }
        return trendIcon;
    }

    public float getmaxP() {                                         /*This function is getting the maximum pressure in a station, we are going through the readings arraylist
                                                                       and checking to see if the current reading is higer then the maxPressure and if so we are making that one
                                                                       the new maxPressure, at the end we return it so that we can display it on our weather card
                                                                    */
        Reading maxPressureReading = null;
        float maxPressure = 0;
        if (readings.size() >= 1) {
            maxPressure = readings.get(0).pressure;
            for (int i = 1; i < readings.size(); i++) {
                if (readings.get(i).pressure > maxPressure) {
                    maxPressure = readings.get(i).pressure;
                }
            }
        } else {
            maxPressure = 0;
        }
        return maxPressure;
    }

    public float getminP() {                                          /*This function is getting the minimum pressure in a station, we are going through the readings arraylist
                                                                       and checking to see if the current reading is lower then the minPressure and if so we are making that one
                                                                       the new minPressure, at the end we return it so that we can display it on our weather card
                                                                    */
        Reading minPressureReading = null;
        float minPressure = 0;
        if (readings.size() >= 1) {
            minPressure = readings.get(0).pressure;
            for (int i = 1; i < readings.size(); i++) {
                if (readings.get(i).pressure < minPressure) {
                    minPressure = readings.get(i).pressure;
                }
            }
        } else {
            minPressure = 0;
        }
        return minPressure;
    }

    public float getminWind() {                                       /*This function is getting the minimum wind speed in a station, we are going through the readings arraylist
                                                                       and checking to see if the current reading is lower then the minWind and if so we are making that one
                                                                       the new minWind, at the end we return it so that we can display it on our weather card
                                                                    */
        Reading minWindReading = null;
        float minWind = 0;
        if (readings.size() >= 1) {
            minWind = readings.get(0).windSpeed;
            for (int i = 1; i < readings.size(); i++) {
                if (readings.get(i).windSpeed < minWind) {
                    minWind = readings.get(i).windSpeed;
                }
            }
        } else {
            minWind = 0;
        }
        return minWind;
    }

    public float getmaxWind() {                                      /*This function is getting the maximum wind speed in a station, we are going through the readings arraylist
                                                                       and checking to see if the current reading is higher then the maxWind and if so we are making that one
                                                                       the new maxWind, at the end we return it so that we can display it on our weather card
                                                                    */
        Reading minWindReading = null;
        float maxWind = 0;
        if (readings.size() >= 1) {
            maxWind = readings.get(0).windSpeed;
            for (int i = 1; i < readings.size(); i++) {
                if (readings.get(i).windSpeed > maxWind) {
                    maxWind = readings.get(i).windSpeed;
                }
            }
        } else {
            maxWind = 0;
        }
        return maxWind;
    }

    public float getmaxTemp() {                                       /*This function is getting the maximum temperature in a station, we are going through the readings arraylist
                                                                       and checking to see if the current reading is higher then the maxTemp and if so we are making that one
                                                                       the new maxTemp, at the end we return it so that we can display it on our weather card
                                                                    */
        Reading maxTempReading = null;
        float maxTemp = 0;
        if (readings.size() >= 1) {
            maxTemp = readings.get(0).temperature;
            for (int i = 1; i < readings.size(); i++) {
                if (readings.get(i).temperature > maxTemp) {
                    maxTemp = readings.get(i).temperature;
                }
            }
        } else {
            maxTemp = 0;
        }
        return maxTemp;
    }

    public float getminTemp() {                                        /*This function is getting the minimum temperature in a station, we are going through the readings arraylist
                                                                       and checking to see if the current reading is lower then the minTemp and if so we are making that one
                                                                       the new minTemp, at the end we return it so that we can display it on our weather card
                                                                    */
        Reading minTempReading = null;
        float minTemp = 0;
        if (readings.size() >= 1) {
            minTemp = readings.get(0).temperature;
            for (int i = 1; i < readings.size(); i++) {
                if (readings.get(i).temperature < minTemp) {
                    minTemp = readings.get(i).temperature;
                }
            }
        } else {
            minTemp = 0;
        }
        return minTemp;
    }


}

