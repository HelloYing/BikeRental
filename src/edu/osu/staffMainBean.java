package edu.osu;
import javax.ejb.EJB;
import examples.cse769.EJB.Service.*;

public class staffMainBean {
private String email;
@EJB
private PeopleService peopleService;
public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

}
