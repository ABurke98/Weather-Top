package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model {
  public String name;
  public float latitude;
  public float longitude;
  public float maxPressure;
  public float minPressure;




  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();

  public Station(String name, float latitude, float longitude) {
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getName() {
    return name;
  }

  public int getLatestWeather() {
    int code;
    code = readings.get(readings.size() - 1).code;
    return code;
  }

  public float getTempCelsius() {
    float tempC = 0;
    if (readings.size() > 0) {
      tempC = readings.get(readings.size() - 1).temperature;
    }
    return tempC;
  }

  public float getTempFarenheit() {
    float tempF = 0;
    float tempC;
    if (readings.size() > 0) {
      tempC = readings.get(readings.size() - 1).temperature;
      tempF = (float) ((tempC * 1.8) + 32);

    }
    return tempF;
  }

  public double getWindChill() {
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

  public int getLatestPressure() {
    int pressure = 0;
    if (readings.size() > 0) {
      pressure = readings.get(readings.size() - 1).pressure;
    }
    return pressure;
  }

  public int getBeaufortSpeed() {
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

  public String getWeather() {
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

  public String getWindDir() {
    float windDire = 0;
    String windDir = " ";
    if (readings.size() > 0) {
      windDire = readings.get(readings.size() - 1).windDirection;

      if (windDire >= 348.75 && windDire <= 11.25) {
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

  public String weatherIcon() {
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

  public String tempIcon() {
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

  public String tempTrendIcon() {
    String trendIcon = " ";
    if (readings.size() >= 3) {
      float t1 = readings.get(readings.size() - 3).temperature;
      float t2 = readings.get(readings.size() - 2).temperature;
      float t3 = readings.get(readings.size() - 1).temperature;
      if (t3 > t2 && t2 > t1) {
        trendIcon += " huge long arrow alternate up icon ";
      }
      else if (t3 < t2 && t2 < t1) {
        trendIcon += " huge long arrow alternate down icon ";
      }
      else {
        trendIcon += " huge arrows alternate horizontal icon";
      }
    }
    return trendIcon;
  }

  public float getmaxP(){

    Reading maxPressure = null;
    if(readings.size() > 0){
      maxPressure = readings.get(0);
      for(Reading reading : readings){
        if (reading.pressure > maxPressure.pressure){
          maxPressure = reading;
        }
      }
    }
    else{
    }
    return maxPressure.pressure;
  }

  public float getminP(){

    Reading minPressure = null;
    if(readings.size() > 0){
      minPressure = readings.get(0);
      for(Reading reading : readings){
        if (reading.pressure < minPressure.pressure){
          minPressure = reading;
        }
      }
    }
    else{
    }
    return minPressure.pressure;
  }

  public float getminWind(){

    Reading minWind = null;
    if(readings.size() > 0){
      minWind = readings.get(0);
      for(Reading reading : readings){
        if (reading.windSpeed < minWind.windSpeed){
          minWind = reading;
        }
      }
    }
    else{
    }
    return minWind.windSpeed;
  }
}

