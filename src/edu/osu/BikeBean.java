package edu.osu;

import java.util.ArrayList;

import javax.ejb.EJB;

import examples.cse769.EJB.Entity.BikeEntity;
import examples.cse769.EJB.Service.BikeService;
public class BikeBean {
	
@EJB
private BikeService bikeService=new BikeService();

private ArrayList<BikeEntity> bikes=new ArrayList<BikeEntity>();

private String message="";
private int id;
private String name;
private String type;
private String description;
private double dailyprice;
private double lowprice;
private double highprice;
private int condition;
private double damagefee;
private double latefee;
//whether this bike is editable or not
private boolean editable=false;

public int getId()
{

	return this.id;
}
public void setId(int i)
{
	this.id=i;
}

public String getName()
{
	return this.name;
}
public void setName(String name)
{
	this.name=name;
}

public String getDescription()
{
	return this.description;
}
public void setDescription(String d)
{
	this.description=d;
}

public double getDailyprice()
{
	return this.dailyprice;
}
public void setDailyprice(double p)
{
	this.dailyprice=p;
}

public int getCondition()
{
	return this.condition;
}
public void setCondition(int c)
{
	this.condition=c;
}

public BikeEntity searchById()
{
	BikeEntity a=bikeService.serachBikeById(id);
	ArrayList<BikeEntity> b=new ArrayList<BikeEntity>();
	b.add(a);
	this.setBikes(b);
	return a;
	
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
public ArrayList<BikeEntity> getBikes() {
	return bikes;
}
public void setBikes(ArrayList<BikeEntity> bikes) {
	this.bikes = bikes;
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
public String getMessage() {
	return message;
}
public void setMessage(String m) {
	this.message=m;
}

public String addBike()
{
	BikeEntity b=new BikeEntity();
	b.setName(name);
	b.setType(type);
	b.setDescription(description);
	b.setDailyprice(dailyprice);
	b.setDamagefee(damagefee);
	b.setLatefee(latefee);
	b.setCondition(condition);
	
	if(bikeService.insertBike(b))
	{
		this.setMessage("Insert Bike successfully!");
		return "successful insert";
	}
	else
	{
		this.setMessage("Fail to insert bike.");
		return "fail insert";
	}
	
}

public String deleteBike()
{
	for(int i=0;i<bikes.size();i++)
	{
		BikeEntity a=bikes.get(i);
		if(a.getId()==id)
		{
			bikes.remove(a);
		}
	}
	if(bikeService.deleteBike(id))
	{
		this.setMessage("delete bike sucessfully!");
		return "successful delete";
	}
	else{
		this.setMessage("fails to delete bike");
		return "fail delete";
	}
}

public void edit(BikeEntity b)
{
	
	b.setEditable(true);
}

public String updateBike(BikeEntity b)
{
	
	b.setEditable(false);
	if(bikeService.updateBike(b))
	{
		this.setMessage("Update Bike successfully!");
		return "successful update";
	}
	else
	{
		this.setMessage("Fail to update Bike. Please try again.");
		return "fail update";
	}
}
public String updateBikePrice()
{
if(bikeService.updateBikePrice(id,dailyprice))
{
	this.setMessage("Update Bike Price successfully!");
	return "successful update";
}
else
{
	this.setMessage("Fail to update Bike Price. Please try again.");
	return "fail update";
}
}

public String updateBikeCondition()
{
	if(bikeService.updateBikeCondition(id,condition))
	{
		this.setMessage("Update Bike Condition Successfully!");
		return "successful update";
	}
	else{
		this.setMessage("Fail to Update Bike Condition. Please try again.");
		return "fail update";
	}
}



public String searchAllBikes()
{
	this.setBikes(bikeService.searchAllBikes());
	if(bikes.isEmpty())
	{
		this.setMessage("No results found!");
		return "not found";
	}
	else{
		this.setMessage("search results: ");
		return "found";
	}
}

public String searchByCondition()
{
	this.setBikes(bikeService.searchBikeByCondition(condition));
	if(bikes.isEmpty())
	{
		this.setMessage("No results found!");
		return "not found";
	}
	else{
		this.setMessage("search results: ");
		return "found";
	}
}

public String searchByPrice()
{
	this.setBikes(bikeService.searchBikeByPrice(lowprice, highprice));
	if(bikes.isEmpty())
	{
		this.setMessage("No results found!");
		return "not found";
		
	}
	else{
		this.setMessage("search results: ");
		return "found";
	}
}

public String searchByType()
{
	this.setBikes(bikeService.searchBikeByType(type));
	if(bikes.isEmpty())
	{
		this.setMessage("No results found!");
		return "not found";
	}
	else
	{
		this.setMessage("search results: ");
		return "found";
	}
}
public boolean isEditable() {
	return editable;
}
public void setEditable(boolean editable) {
	this.editable = editable;
}


}
