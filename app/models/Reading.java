package models;

//The Reading model holds the fields for the reading as well as the constructor for it

import javax.persistence.Entity;

import play.db.jpa.Model;


@Entity
public class Reading extends Model {

    public String date;
    public int code;
    public float temperature;
    public float windSpeed;
    public float windDirection;
    public int pressure;

    public Reading(String date, int code, float temp, float wind, float windDirection, int pressure) {   //This data gets passed in by the addReport function
        this.date = date;
        this.code = code;
        this.temperature = temp;
        this.windSpeed = wind;
        this.windDirection = windDirection;
        this.pressure = pressure;
    }


}
