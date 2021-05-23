package models;

//The Member model holds date relating to the member.

import controllers.Accounts;
import play.Logger;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Model {
    public String firstname;
    public String lastname;
    public String email;
    public String password;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Station> stations = new ArrayList<Station>();   //Declaring the stations Arraylist

    public Member(String firstname, String lastname, String email, String password) {       // Setting the parameters for the member
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public static void updateUser(String firstname, String lastname, String email, String password) {
        Logger.info("Editing user account");
        Member member = Accounts.getLoggedInMember();
        member.firstname = firstname;
        member.lastname = lastname;
        member.email = email;
        member.password = password;
        member.save();
    }

    public static Member findByEmail(String email) {
        return find("email", email).first();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}