package models;

import javax.persistence.Entity;
import java.util.Date;

import play.db.jpa.Model;



@Entity
public class Reading extends Model
{

  public String date;
  public int code;
  public float temperature;
  public float windSpeed;
  public float windDirection;
  public int pressure;
  
  public Reading(String date, int code, float temp, float wind, float windDirection, int pressure)
  {
    this.date = date;
    this.code = code;
    this.temperature = temp;
    this.windSpeed = wind;
    this.windDirection = windDirection;
    this.pressure = pressure;
  }



}
