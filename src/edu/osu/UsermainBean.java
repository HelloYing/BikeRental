/**
 * 
 */
package edu.osu;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import examples.cse769.EJB.Service.*;
import examples.cse769.EJB.Entity.*;

/**
 * @author Siwei
 *
 */
public class UsermainBean
{
	@EJB
	private BikeService bikeService=new BikeService();
	@EJB
	private RentService rentService=new RentService();
	
	private double price;
	public double getPrice(){
		return price;
	}
	public void setPrice(double price){
		this.price=price;
	}

	  private ArrayList<ArrayList<String>> bikelist=new ArrayList<ArrayList<String>>();
		
		public ArrayList<ArrayList<String>> getBikelist(){
			return bikelist;
		}
		public void setBikelist(ArrayList<ArrayList<String>> bikelist){
			this.bikelist=bikelist;
		}
	
	public String viewBikes(){		
		bikelist=bikeService.searchAllBike();
		System.out.println("front end="+bikelist.size());
		return "allbikes";
	}
	
	private LoginBean logBean;
	public LoginBean getLogBean() {
		return logBean;
	}
	public void setLogBean(LoginBean logBean) {
		this.logBean = logBean;
	}
	
	/*@EJB
	private ProfileService profileService;
	
	private ProfileBean profileBean;
	public ProfileBean getProfileBean(){
		return profileBean;
	}
	public void setProfileBean(ProfileBean profileBean){
		this.profileBean=profileBean;
	}
	
	/*
	public String profile(){
		
		System.out.println("logBean id="+logBean.getId());
		
		Profile profile=profileService.search("1345075374@qq.com");
		
		profileBean.setFirstname(profile.getFirstname());
		profileBean.setLastname(profile.getLastname());
		profileBean.setPhone(profile.getPhone());
		profileBean.setBirthday(profile.getBirthday());
		profileBean.setAddress(profile.getAddress());
		profileBean.setMessage("Update successfully! ");
		return "profile";
	}
	*/
	

	public ArrayList<ArrayList<String>> availablebikelist=new ArrayList<ArrayList<String>>();
	
	public ArrayList<ArrayList<String>> getAvailablebikelist(){
		return availablebikelist;
	}
	public void setAvailablebikelist(ArrayList<ArrayList<String>> availablebikelist){
		this.availablebikelist=availablebikelist;
	}

	
	
}
