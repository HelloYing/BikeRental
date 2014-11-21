package edu.osu;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import examples.cse769.EJB.Service.PeopleService;

/**
 * @author Siwei Dng
 *
 */
public class RegisterBean
{
	@EJB
	private PeopleService peopleService=new PeopleService();
	private String email;
	private String password;
	private String conpassword;
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email=email;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getConpassword(){
		return conpassword;
	}
	public void setConpassword(String conpassword){
		this.conpassword=conpassword;
	}
	
	public String register(){
		if(!password.equals(conpassword)){
			return "Passwords are different!";
		}
		CheckInput checkInput=new CheckInput();
		if(checkInput.checkEmail(email) && checkInput.checkPassword(password)){
			return peopleService.insert(email, password);
		}else if(!checkInput.checkEmail(email) && !checkInput.checkPassword(password)){
			return "Please use a valid email and a valid password.";
		}else if(!checkInput.checkEmail(email)){
			return "Please use a valid email.";
		}else{
			return "Please use a valid password.";
		}
	}
}
