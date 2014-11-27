/**
 * 
 */
package edu.osu;

import java.sql.Date;
import javax.ejb.EJB;

import examples.cse769.EJB.Service.*;
import examples.cse769.EJB.Entity.*;

/**
 * @author Siwei
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

    private Date birthday;
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

    private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	public String update() {		
		String ret = profileService.profile(logBean.getId(), firstname, lastname, phone, birthday, address);
		if(ret=="true"){
			return "Profile updates success.";
		}else{
			return "Profile updates failed, please try again.";
		}
	}
	
	
}
