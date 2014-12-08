package edu.osu;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.ejb.EJB;

import examples.cse769.EJB.Service.*;
import examples.cse769.EJB.Entity.*;

public class AvailableBikeBean {
	@EJB
	private RentService rentService=new RentService();
	@EJB
	private PeopleService peopleService=new PeopleService();
	@EJB
	private BikeService bikeService=new BikeService();
	
	private double lowprice;
	private double highprice;
	
	
	private ArrayList<ArrayList<String>> availablebikelist=new ArrayList<ArrayList<String>>();
	
	public ArrayList<ArrayList<String>> getAvailablebikelist(){
		return availablebikelist;
	}
	public void setAvailablebikelist(ArrayList<ArrayList<String>> availablebikelist){
		this.availablebikelist=availablebikelist;
	}
	
	private ArrayList<BikeEntity> rentablebikes=new ArrayList<BikeEntity>();
	
	
	private LoginBean logBean;
	public LoginBean getLogBean() {
		return logBean;
	}
	public void setLogBean(LoginBean logBean) {
		this.logBean = logBean;
	}
	
	private String begDate;
	public String getBegDate(){
		return begDate;
	}
	public void setBegDate(String begDate){
		this.begDate=begDate;
	}
	private String endDate;
	public String getEndDate(){
		return endDate;
	}
	public void setEndDate(String endDate){
		this.endDate=endDate;
	}
	
	private UsermainBean usermainBean;
	public UsermainBean getUsermainBean(){
		return usermainBean;
	}
	public void setUsermainBean(UsermainBean usermainBean){
		this.usermainBean=usermainBean;
	}
	
	private int bikeId;
	public int getBikeId(){
		return bikeId;
	}
	public void setBikeId(int bikeId){
		this.bikeId=bikeId;
	}
	
	public String res;
	public String getRes(){
		return res;
	}
	public void setRes(String res){
		this.res=res;
	}
	
	
	public String findAvailableBikes() throws ParseException{
		CheckInput checkInput=new CheckInput();
		if(checkInput.checkDate(begDate) && checkInput.checkDate(endDate)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date bin=new Date(sdf.parse(begDate).getTime());
			Date end=new Date(sdf.parse(endDate).getTime());
			ArrayList<BikeEntity> bikes=bikeService.searchBikeByPrice(lowprice, highprice);
			for(BikeEntity bike: bikes){
				if(bikeService.searchAvailableBike(bike.getId(), bin, end)){
					
					ArrayList<String> val=new ArrayList<String>();
					val.add(bike.getName());
					val.add(bike.getDescription());
					val.add(""+bike.getCondition());
					val.add(""+bike.getDailyprice());
					val.add(""+bike.getLatefee());
					val.add(""+bike.getDamagefee());
					availablebikelist.add(val);
					
					rentablebikes.add(bike);
				}
			}
			if(availablebikelist.size()==0){
				return "noavailable";
			}
			return "available";
		}else{
			return "noavailable";
		}
		
	}
	
	public void rent(String email,BikeEntity b) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date bin=new Date(sdf.parse(begDate).getTime());
		Date end=new Date(sdf.parse(endDate).getTime());
		
		int fee=(int)((end.getTime()-bin.getTime())/(24 * 60 * 60 * 1000))*(int)b.getDailyprice();
		//int ori=logBean.getPoint();
		People user=peopleService.searchUserByEmail(email);
		int userid=user.getId();
		int ori=user.getPoint();
		
		if(ori>=fee){
			if(peopleService.updatePoint(userid, fee)){
				logBean.setPoint(ori-fee);
				res=rentService.insert(userid, b.getId(), fee, bin, end);
				this.setRes("Successful rent. Thank you!");
			}else{
				res="Sorry, rent failed, please rent again.";
				
			}						
		}else{
			res="Sorry, you don't have enough points.";
		}
		
	}
	public double getLowprice() {
		return lowprice;
	}
	public void setLowprice(double lowprice) {
		this.lowprice = lowprice;
	}
	public double getHighprice() {
		return highprice;
	}
	public void setHighprice(double highprice) {
		this.highprice = highprice;
	}
	
	public ArrayList<BikeEntity> getRentablebikes() {
		return rentablebikes;
	}
	public void setRentablebikes(ArrayList<BikeEntity> rentablebikes) {
		this.rentablebikes = rentablebikes;
	}
	

}
