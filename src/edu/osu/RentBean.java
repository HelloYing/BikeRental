package edu.osu;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import javax.ejb.EJB;

import examples.cse769.EJB.Service.*;
import examples.cse769.EJB.Entity.*;
public class RentBean {
	
private ArrayList<RentEntity> rents=new ArrayList<RentEntity>();
// message about rent update
private String updateMessage;
//message about rent search
private String searchMessage;
private int id;
private int userid;
private String type;
private double price;
private double damagefee;
private double latefee;
private String beginDate;
private String endDate;
@EJB
private PeopleService peopleService=new PeopleService();

@EJB
private RentService rentService=new RentService();

public String updateRent()
{
	int userid=rentService.updateRent(id, latefee, damagefee);
	if(userid!=-1)
	{
		int point=(int)(damagefee+latefee);
		//deduct points from user account
		peopleService.updatePoint(userid, point);
		this.setUpdateMessage("update successfully!");
		return "successful update";
	}
	else{
		this.setUpdateMessage("fail to update!");
		return "fail update";
	}
}

public String searchRentByBikeType()
{
	this.setRents(rentService.searchRentByBikeType(type));
	if(rents.isEmpty())
	{
		this.setSearchMessage("No results found!");
		return "no found";
	}
	else
	{
		this.setSearchMessage("search results: ");
		return "found";
	}
}

public String searchRentById()
{
	RentEntity rent=rentService.searchRentById(id);
	if(rent!=null)
	{
		ArrayList<RentEntity> a=new ArrayList<RentEntity>();
		a.add(rent);
		this.setRents(a);
		
		/*this.setDamagefee(rent.getDamagefee());
		this.setId(rent.getId());
		this.setBeginDate(rent.getDatebegin().toString());
		this.setEntDate(rent.getDateend().toString());
		this.setPrice(rent.getPrice());
		this.setLatefee(rent.getLatefee());*/
		
		this.setSearchMessage("result:");
		return "found";
	}
	else
	{
		this.setSearchMessage("Rent not found!");
		return "no found";
	}
	
}
public String searchRentDate() throws ParseException
{
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date bin=new Date(sdf.parse(beginDate).getTime());
	Date end=new Date(sdf.parse(endDate).getTime());
	
	this.setRents(rentService.searchRentByDate(bin,end));
	if(rents.isEmpty())
	{
		this.setSearchMessage("No reusults found!");
		return "no found";
	}
	else
	{
		this.setSearchMessage("search resulta: ");
		return "found";
	}
	
	
	
}

public String searchRentByUser(String email)
{
	this.setRents(rentService.searchRentByUser(email));
	if(rents.isEmpty())
	{
		this.setSearchMessage("No reusults found!");
		return "not found";
	}
	else
	{
		this.setSearchMessage("search results: ");
		return "orders";
	}
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public double getDamagefee() {
	return damagefee;
}
public void setDamagefee(double damagefee) {
	this.damagefee = damagefee;
}
public double getLatefee() {
	return latefee;
}
public void setLatefee(double latefee) {
	this.latefee = latefee;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public ArrayList<RentEntity> getRents() {
	return rents;
}

public void setRents(ArrayList<RentEntity> rents) {
	this.rents = rents;
}

public void setBeginDate(String d)
{
	this.beginDate=d;
}

public String getBeginDate()
{
	return this.beginDate;
}

public void setEntDate(String d)
{
	this.endDate=d;
}

public String getEndDate()
{
	return this.endDate;
}

public String getUpdateMessage() {
	return updateMessage;
}

public void setUpdateMessage(String updateMessage) {
	this.updateMessage = updateMessage;
}

public String getSearchMessage() {
	return searchMessage;
}

public void setSearchMessage(String searchMessage) {
	this.searchMessage = searchMessage;
}


}
