package controllers;


//The about Controller is used to render the about page


import play.*;
import play.mvc.*;

public class About extends Controller {
    public static void index() {               //Index get's called via a route and then renders and displays about.html on screen
        Logger.info("Rendering about");
        render("about.html");
    }
}
