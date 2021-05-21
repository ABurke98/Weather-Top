package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Reading extends Model
{
  
  public Reading(int code, float temp, float wind, float windDirection, int pressure)
  {
    this.code = code;
    this.temperature = temp;
    this.windSpeed = wind;
    this.windDirection = windDirection;
    this.pressure = pressure;
  }

  public int code;
  public float temperature;
  public float windSpeed;
  public float windDirection;
  public int pressure;


}
