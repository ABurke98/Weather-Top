package controllers;

// The admin class allows us to view every reading listed in the database

import java.util.List;
import models.Reading;
import play.mvc.Controller;

public class Admin extends Controller
{
    public static void index() {                    /*Index is called via a route, List<Reading> readings = Reading.findAll(); looks through the readings arraylist,
                                                       we then pass that information to the view so that we can display it there. */
        List<Reading> readings = Reading.findAll();
        render ("admin.html", readings);
    }
}
