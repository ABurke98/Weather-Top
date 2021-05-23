package controllers;

//The editUser controller is used to display the editUser.html view

import play.Logger;
import play.mvc.Controller;

public class EditUser extends Controller {
    public static void index() {
        Logger.info("Rendering Editor");
        render("editUser.html");
    }
}
