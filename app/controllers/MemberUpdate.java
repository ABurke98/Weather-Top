package controllers;

// In the member update controller we have a function for updating the users account.

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class MemberUpdate extends Controller {    /* Update user takes in data through a form via a route, we take the current logged in user and then use the data from the form
                                                    To update the user fields, at the end the member is saved and then directed back to the login page so they can log in using
                                                    new data.
                                                 */
    public static void updateUser(String firstname, String lastname, String email, String password) {
        Logger.info("Editing user account");
        Member member = Accounts.getLoggedInMember();
        member.firstname = firstname;
        member.lastname = lastname;
        member.email = email;
        member.password = password;
        member.save();
        redirect("/login");
    }

}