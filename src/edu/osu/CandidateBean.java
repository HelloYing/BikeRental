/**
 * 
 */
package edu.osu;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

//import org.apache.log4j.Logger;

import examples.cse769.EJB.Service.JobApply;
import examples.cse769.EJB.Service.HelloService;
import examples.cse769.EJB.Service.JobListing;
import examples.cse769.EJB.Service.JobsApplied;


/**
 * @author Reddiah
 *
 */
public class CandidateBean
{
	private List<List<String>> candidates = new ArrayList<List<String>>();

	public List<List<String>> getCandidates() {
		return candidates;
	}
	public void setCandidates(List<List<String>> candidates) {
		this.candidates = candidates;
	}
	@EJB
	HelloService hs;
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	private String location;
	private String pay;
	private String technology;
	private String user;
	
	public String find() {
		String dummy = "&^*%";
	
		if(title.isEmpty())
			title = dummy;
		
		if(location.isEmpty())
			location = dummy;
		
		if(pay.isEmpty())
			pay = dummy;
		
		if(technology.isEmpty())
			technology = dummy;
			
		candidates = hs.findCandidates(title, location, pay, technology);
		
		clear();
		if(candidates.isEmpty())
			return "false";
		
		else
		{return "found";}
	
	}
	
	void clear()
	{
		title = "";
		location = "";
		pay = "";
		technology = "";
		
	}
	
	}

	

