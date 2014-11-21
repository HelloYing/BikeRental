/**
 * 
 */
package edu.osu;

import java.io.File;
import java.util.Arrays;

import javax.ejb.EJB;

import examples.cse769.EJB.Service.JobApply;
import examples.cse769.EJB.Service.HelloService;
import examples.cse769.EJB.Service.JobListing;


/**
 * @author Reddiah
 *
 */
public class ProfileBean
{
	private LoginBean logBean;
	public LoginBean getLogBean() {
		return logBean;
	}


	public void setLogBean(LoginBean logBean) {
		this.logBean = logBean;
	}


	@EJB 
	private HelloService helloService;
	
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


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	private String title;
    private String location;
    private String pay;
    private String technology;
    private String user;

    //baluC
   // private UploadedFile file;
    
    //baluC

	public String update() {
	    
		
		String ret = helloService.update(logBean.getName(), title, location, pay, technology);
		if(ret.equalsIgnoreCase("success"))
		return "update";
		else
			return "fail";

	}


	
}
