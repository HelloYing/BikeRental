/**
 * 
 */
package edu.osu;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;

import examples.cse769.EJB.Service.*;
import examples.cse769.EJB.Entity.*;

/**
 * @author Siwei
 *
 */
public class ProfileBean
{
	
	private String message;
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String s)
	{
		message=s;
	}
	
	private LoginBean logBean;
	public LoginBean getLogBean() {
		return logBean;
	}
	public void setLogBean(LoginBean logBean) {
		this.logBean = logBean;
	}


	@EJB 
	private ProfileService profileService;
	
	private String firstname;
    public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	private String lastname;
    public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

    private String birthday;
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

    private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

//update the profile
	public void update(String email) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birth=new Date(sdf.parse(birthday).getTime());
		
		boolean ret = profileService.update(email, firstname, lastname, phone, birth, address);
		if(ret){
			this.setMessage("Update profile successfully!");
			 
		}else{
			this.setMessage("Update failed.");
			 
		}
	}
	
	//search profile by email
public String profile(String email){
		
		//System.out.println("logBean id="+logBean.getId());
	   //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  // Date birth=new Date(sdf.parse(birthday).getTime());
		Profile profile=profileService.search(email);
		this.setFirstname(profile.getFirstname());
		this.setLastname(profile.getLastname());
		this.setPhone(profile.getPhone());
		this.setBirthday(profile.getBirthday().toString());
		this.setAddress(profile.getAddress());
		return "profile";
	}
	
	
}
