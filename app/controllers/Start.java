package controllers;

// The start controller is used to display the start.html view.

import play.Logger;
import play.mvc.Controller;

public class Start extends Controller {
    public static void index() {
        Logger.info("Rendering Start");
        render("start.html");
    }
}
