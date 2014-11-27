package edu.osu;
import java.util.ArrayList;

import javax.ejb.EJB;

import examples.cse769.EJB.Entity.EventEntity;
import examples.cse769.EJB.Service.EventService;
public class EventBean {
@EJB
private EventService eventService;

private ArrayList<EventEntity> events;
private int id;
private String name;
private String type;
private String description;
private String address;
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

public String postEvent()
{
	EventEntity event=new EventEntity();
	event.setName(name);
	event.setType(type);
	event.setDescription(description);
	event.setAddress(address);
	
	if(eventService.insertEvent(event))
	{
		return "successful";
	}
	else
		return "fail";
}


}
