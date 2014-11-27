package edu.osu;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;

import examples.cse769.EJB.Service.*;
import examples.cse769.EJB.Entity.*;

public class AvailableBikeBean {
	@EJB
	private RentService rentService=new RentService();
	
	private LoginBean logBean;
	public LoginBean getLogBean() {
		return logBean;
	}
	public void setLogBean(LoginBean logBean) {
		this.logBean = logBean;
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
	
	public void rent() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date bin=new Date(sdf.parse(usermainBean.getBegDate()).getTime());
		Date end=new Date(sdf.parse(usermainBean.getEndDate()).getTime());
		int fee=(int)((end.getTime()-bin.getTime())/(24 * 60 * 60 * 1000))*(int)usermainBean.getPrice();
		int ori=logBean.getPoint();
		if(ori>=fee){
			logBean.setPoint(ori-fee);
			res=rentService.insert(logBean.getId(), bikeId, fee, bin, end);
		}else{
			res="Sorry, you don't have enough points.";
		}
		
	}

}
