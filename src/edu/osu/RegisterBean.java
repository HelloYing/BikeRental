package edu.osu;

import javax.ejb.EJB;

import examples.cse769.EJB.Service.*;

/**
 * @author Siwei Dng
 *
 */
public class RegisterBean
{
	@EJB
	private PeopleService peopleService=new PeopleService();
	@EJB
	private ProfileService profileService=new ProfileService();
	//register message
	private String message;
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
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String register(){
		if(!password.equals(conpassword)){
			this.setMessage("Passwords are different!");
			return "Passwords are different!";
		}
		CheckInput checkInput=new CheckInput();
		if(checkInput.checkEmail(email) && checkInput.checkPassword(password)){
			this.setMessage("Register Successfully! Please Login.");			
			String res=peopleService.insert(email, password);
			int peopleId=peopleService.searchUserByEmail(email).getId();
			profileService.insert(peopleId,email);
			return res;
		}else if(!checkInput.checkEmail(email) && !checkInput.checkPassword(password)){
			this.setMessage("Please use a valid email and a valid password.");
			return "Please use a valid email and a valid password.";
		}else if(!checkInput.checkEmail(email)){
			this.setMessage("Please use a valid email.");
			return "Please use a valid email.";
		}else{
			this.setMessage("Please use a valid password.");
			return "Please use a valid password.";
		}
	}
}
