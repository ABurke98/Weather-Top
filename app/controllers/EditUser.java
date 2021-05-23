package controllers;

import play.Logger;
import play.mvc.Controller;

public class EditUser extends Controller
{
    public static void index() {
        Logger.info("Rendering Editor");
        render ("editUser.html");
    }
}
