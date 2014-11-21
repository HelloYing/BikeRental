/**
 * 
 */
package edu.osu;

import javax.ejb.EJB;

import examples.cse769.EJB.Service.HelloService;
import examples.cse769.EJB.Service.AddJob;

import java.io.IOException;
import java.util.logging.*;


/**
 * @author Reddiah
 *
 */
public class AddBean
{
	private static Logger logger = Logger.getLogger("edu.osu.AddBean");
	private static FileHandler fh;
	
	private LoginBean logBean;
	public LoginBean getLogBean() {
		return logBean;
	}


	public void setLogBean(LoginBean logBean) {
		this.logBean = logBean;
	}


	@EJB 
	private AddJob addJob;
    private String title;
    private String location;
    private String pay;
    private String technology;


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getPay() {
		return pay;
	}


	public void setPay(String pay) {
		this.pay = pay;
	}


	public String getTechnology() {
		return technology;
	}


	public void setTechnology(String technology) {
		this.technology = technology;
	}


	public String add() {
		
		String[] params = new String[5];
		params[0] = title;
		params[1] = location;
		params[2] = pay;
		params[3] = technology;
		params[4] = logBean.getName();

		try {
			  if(fh == null)
			  fh = new FileHandler("final.txt");
			  fh.setFormatter(new SimpleFormatter());
		  		logger.addHandler(fh);
		 	 	logger.setLevel(Level.ALL);
		  		logger.entering("AddBean", "add", params);
			   }
			catch (IOException e) {
			e.printStackTrace();
		}
	String ret = addJob.addJob(title, location, pay, technology, logBean.getName());
		
    logger.exiting("AddBean", "add", ret);

	if(!ret.equalsIgnoreCase("Exists"))
		return "true";
		else 
			return "false";

		
	 //return "false";
	}
	
}
