package controllers;

//The Dashboard controller is one of the main controllers in the app.

import java.util.List;
import java.util.*;
import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static void index(){                         /* When index is called via a route we get the logged in member, and then we display the stations
                                                         that are assosciated with that member, Collections.sort is run to display the Arraylist in alphabetical order.
                                                         Finally we pass that data into the dashboard view so that we can display it there.
                                                       */

    Logger.info("Rendering Dasboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    Collections.sort(stations, Comparator.comparing(Station::getName));
    render ("dashboard.html", stations);
  }

  public static void deleteStation (Long id){           /* Delete station works similar to the index function, wherein we get the logged in user and we then get the stations
                                                           assosciated with that user, member.stations.remove is called with the specified stations ID attached which removes
                                                           it from the members account, the member account is then updated and the station is then removed from the Arraylist
                                                       */

    Logger.info("Deleting a Playlist");
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    member.stations.remove(station);
    member.save();
    station.delete();
    redirect ("/dashboard");
  }

  public static void addStation (String name, float latitude, float longitude){  /* Add station works nearly the same as delete station but instead of removing data we take in data
                                                                                   from a form via a route and then we create a new station with that data and save it to the logged
                                                                                   in members account.
                                                                                */

    Logger.info("Adding a Playlist");
    Member member = Accounts.getLoggedInMember();
    Station station = new Station(name,latitude,longitude);
    member.stations.add(station);
    member.save();
    redirect ("/dashboard");
  }
}