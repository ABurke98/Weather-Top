package controllers;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.*;
import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;


public class StationCtrl extends Controller
{

    public String currentTime;

    public static void index(Long id) {
        Station station = Station.findById(id);
        Logger.info("Station ID = " + id);
        render("station.html", station);
    }

    public static String getDate(){
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy  hh:mm:ss");
        String stringDate= DateFor.format(date);
        return stringDate;
    }

    public static void addReport(long id, int code, float temp, float wind, float windDirection, int pressure){
        String currentTime = getDate();
        Reading reading = new Reading(currentTime, code, temp, wind, windDirection, pressure);
        Station station = Station.findById(id);
        station.readings.add(reading);
        System.out.println(currentTime);
        station.save();
        redirect("/stations/" + id);
    }

    public static void deleteReading(Long id, Long readingid){
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info("Removing  " + reading.code);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        redirect("/stations/" + id);
    }




}
