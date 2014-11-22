package edu.osu;

import java.io.IOException;
import java.util.logging.*;
import javax.ejb.EJB;

import examples.cse769.EJB.Service.*;

/**
 * @author Siwei
 *
 */
public class LoginBean
{

    private static Logger logger = Logger.getLogger("edu.osu.LoginBean");
    private static FileHandler fh;
	@EJB 
	private PeopleService peopleService;
	private int id;
    private String email;
    private String password;
    private String name;

	public String login() {

 	   String[] params = new String[3];
 	   params[0] = email;
 	   params[1] = password;
 	   try {
 		   if(fh == null)
 	       fh = new FileHandler("final.txt");//"mylog.txt"
 	       fh.setFormatter(new SimpleFormatter());
           logger.addHandler(fh);
           logger.setLevel(Level.ALL);
           logger.entering("LoginBean", "Email", params);
 	   }
 		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int[] res = peopleService.search(email, password);
	    logger.exiting("LoginBean", "userName", res);

		if (res[0]!=0)
		{
				
			id=res[1];
		}
		return ""+res[0];
	}
	
	public int getId (){
        return id;
    }

    public void setId (final int id){
        this.id = id;
    } 
    
	public String getEmail (){
	        return email;
	}
	    
	public void setEmail (final String email){
	        this.email = email;
	}
	    
	public String getPassword (){
	        return password;
	}
	    
	public void setPassword (final String password){
	        this.password = password;
	}
	
	public String getName(){
		return name;
	}
	
}
