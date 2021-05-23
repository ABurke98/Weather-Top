package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import models.Member;
import play.Logger;
import play.mvc.Controller;

public class MemberUpdate extends Controller
{

    public static void updateUser(String firstname, String lastname, String email, String password){
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