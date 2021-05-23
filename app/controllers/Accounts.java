package controllers;

/*
The Accounts controller handles all data relating to accounts, The data from the Member model gets passed into here where it can be
processed via the different methods available here.
 */

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller   //Declaring the Account controller
{
    public static void signup() {
        render("signup.html");
    }

    public static void login() {
        render("login.html");
    }

    public static void register(String firstname, String lastname, String email, String password){  /*Register gets data from the signup view, the data gets passed from a form
                                                                                                    on signup into this method via a route which then populates creates and saves a new member
                                                                                                   */
        Logger.info("Registering new user " + email);
        Member member = new Member(firstname, lastname, email, password);
        member.save();
        redirect("/");
    }

    public static void authenticate(String email, String password){
                                                                                       /*The Authenticate method looks for the members email and then checks the stored password
                                                                                        against the email and if true authenticates the user and starts a new session
                                                                                       */
        Logger.info("Attempting to authenticate with " + email + ":" + password);

        Member member = Member.findByEmail(email);
        if ((member != null) && (member.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            redirect("/dashboard");
        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    public static void logout(){
                                                                //Logout will clear the session and logout the user and redirect back to the homepage
        session.clear();
        redirect("/");
    }

    public static Member getLoggedInMember(){
                                                                   /* the getLoggedInMember function declares a new member object as null so that it can hold the deta grabbed
                                                                   on line 66 & 67, member is returned so that we can get the member ID.
                                                                    */
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }
}