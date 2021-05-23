package controllers;

// The Station controller handles various bits of data for the stations and has functions to update stations, delete stations and get the current date and time for each report made.

import java.text.SimpleDateFormat;
import java.util.*;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;


public class StationCtrl extends Controller {
    public static void index(Long id) {                                /* index here will recieve the ID for a station via a route, with that ID we will find the station and then
                                                                           Load the station into the view for displaying.
                                                                         */
        Station station = Station.findById(id);
        Logger.info("Station ID = " + id);
        render("station.html", station);
    }

    public static String getDate() {                                     /* The getDate function is used for getting the date and time for each report, we create a new date object
                                                                           each time a report is made, we format the date so that it's easily readable for the user and then we return it
                                                                           so that we can display it on the station page.
                                                                         */
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy  hh:mm:ss");
        String stringDate = DateFor.format(date);
        return stringDate;
    }

    public static void addReport(long id, int code, float temp, float wind, float windDirection, int pressure) {     /* Simaler to other Add functions in this app we are pulling
                                                                                                                       data from a form via a route and adding it to the specified
                                                                                                                       station and then saving the station.
                                                                                                                     */
        String currentTime = getDate();
        Reading reading = new Reading(currentTime, code, temp, wind, windDirection, pressure);
        Station station = Station.findById(id);
        station.readings.add(reading);
        System.out.println(currentTime);
        station.save();
        redirect("/stations/" + id);
    }

    public static void deleteReading(Long id, Long readingid) {                    /* Here we are getting a reading within a station to delete, so we get the ID for the station and
                                                                                     then we get the report id, we can use both id's to grab the specific reading that we want to delete
                                                                                     once removed the station is saved and then the reading get's deleted from the database.
                                                                                   */
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info("Removing  " + reading.code);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        redirect("/stations/" + id);
    }


}
