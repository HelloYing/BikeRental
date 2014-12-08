package edu.osu;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import javax.ejb.EJB;

import examples.cse769.EJB.Entity.BikeEntity;
import examples.cse769.EJB.Entity.EventEntity;
import examples.cse769.EJB.Service.EventService;
public class EventBean {
@EJB
private EventService eventService;

private String searchmessage="";
private String addchmessage="";
private String updatechmessage="";
private ArrayList<EventEntity> events=new ArrayList<EventEntity>();
private int id;
private String name;
private String type;
private String description;
private String address;

private String leftDate;
private String rightDate;
private String eventDate;

private boolean editable;

public ArrayList<EventEntity> getEvents() {
	return events;
}
public void setEvents(ArrayList<EventEntity> events) {
	this.events = events;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}



public String postEvent() throws ParseException
{
	EventEntity event=new EventEntity();
	event.setName(name);
	event.setType(type);
	event.setDescription(description);
	event.setAddress(address);
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date d=new Date(sdf.parse(eventDate).getTime());
	
	event.setDate(d);
	
	if(eventService.insertEvent(event))
	{
		this.setAddchmessage("insert event successfully!");
		return "successful insert";
	}
	else{
		this.setAddchmessage("Fail to insert event.");
		return "fail insert";
	}
}

public String searchAllEvents()
{
	this.setEvents(eventService.searchAllEvents());
	if(events.isEmpty())
	{
		this.setSearchmessage("No results found.");
		return "not found";
	}
	else
	{
		this.setSearchmessage("search results: ");
		return "found";
	}
	
}

public String searchEventByType()
{
	this.setEvents(eventService.searchEventByType(type));
	if(events.isEmpty())
	{
		this.setSearchmessage("No results found.");
		return "not found";
	}
	else
	{
		this.setSearchmessage("search results: ");
		return "found";
	}
}

public String searchEventByDate() throws ParseException
{
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date d1=new Date(sdf.parse(leftDate).getTime());
	Date d2=new Date(sdf.parse(rightDate).getTime());
	
	this.setEvents(eventService.searchEventByDate(d1,d2));
	if(events.isEmpty())
	{
		this.setSearchmessage("No results found.");
		return "not found";
	}
	else
	{
		this.setSearchmessage("search results: ");
		return "found";
	}
}
public String getLeftDate() {
	return leftDate;
}
public void setLeftDate(String leftDate) {
	this.leftDate = leftDate;
}
public String getRightDate() {
	return rightDate;
}
public void setRightDate(String rightDate) {
	this.rightDate = rightDate;
}
public String getEventDate() {
	return eventDate;
}
public void setEventDate(String eventDate) {
	this.eventDate = eventDate;
}
public String getSearchmessage() {
	return searchmessage;
}
public void setSearchmessage(String searchmessage) {
	this.searchmessage = searchmessage;
}
public String getAddchmessage() {
	return addchmessage;
}
public void setAddchmessage(String addchmessage) {
	this.addchmessage = addchmessage;
}
public String getUpdatechmessage() {
	return updatechmessage;
}
public void setUpdatechmessage(String updatechmessage) {
	this.updatechmessage = updatechmessage;
}
public boolean isEditable() {
	return editable;
}
public void setEditable(boolean editable) {
	this.editable = editable;
}

public String deleteEvent()
{
	for(int i=0;i<events.size();i++)
	{
		EventEntity a=events.get(i);
		if(a.getId()==id)
		{
			events.remove(a);
		}
	}
	if(eventService.deleteEvent(id))
	{
		this.setUpdatechmessage("delete bike sucessfully!");
		return "successful delete";
	}
	else{
		this.setUpdatechmessage("fails to delete bike");
		return "fail delete";
	}
}

public void edit(EventEntity b)
{
	
	b.setEditable(true);
}

public String updateEvent(EventEntity b)
{
	
	b.setEditable(false);
	if(eventService.updateEvent(b))
	{
		this.setUpdatechmessage("Update Event successfully!");
		return "successful update";
	}
	else
	{
		this.setUpdatechmessage("Fail to update Event. Please try again.");
		return "fail update";
	}
}



}
