/**
 * 
 */
package edu.osu;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;



import examples.cse769.EJB.Service.HelloService;
import examples.cse769.EJB.Service.JobApply;
import examples.cse769.EJB.Service.JobListing;
import examples.cse769.EJB.Service.ProfileEmployer;


/**
 * @author Reddiah
 *
 */
public class WelcomeBean
{
	
	@EJB 
	private ProfileEmployer profileEmployer;
	private LoginBean logBean;	//is it required now---make changes in property type code in faces also
	public LoginBean getLogBean() {
		return logBean;
	}

	public void setLogBean(LoginBean logBean) {
		this.logBean = logBean;
	}
	
	private ProfileEmployerBean profileEmployerBean;

	
	public ProfileEmployerBean getProfileEmployerBean() {
		return profileEmployerBean;
	}

	public void setProfileEmployerBean(ProfileEmployerBean profileEmployerBean) {
		this.profileEmployerBean = profileEmployerBean;
	}

	private ProfileBean profileBean;
	
	public ProfileBean getProfileBean() {
		return profileBean;
	}

	public void setProfileBean(ProfileBean profileBean) {
		this.profileBean = profileBean;
	}

	
    private List<List<String>> jobs = new ArrayList<List<String>>();

	
	public List<List<String>> getJobs() {
		return jobs;
	}

	public void setJobs(List<List<String>> jobs) {
		this.jobs = jobs;
	}

	@EJB 
	private HelloService helloService;
	@EJB
	private JobListing jobListing;
	
	@EJB
	private JobApply jobApply;
    private String title;
    private String location;

    private String aid;//chk

    
    public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}


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

	public String search() {
		
		jobs = jobListing.findJob(title, location);
		
		if(jobs.isEmpty())
			return "false";
		
		else
		{return "true";}
	}
	
	
	public String profile() {
		
		String[] results = helloService.search(logBean.getName());
		
		if(results[0].equalsIgnoreCase("no"))
			return "false";
		
		else
		{
			profileBean.setTitle(results[0]);
			profileBean.setLocation(results[1]);
			profileBean.setTechnology(results[2]);
			profileBean.setPay(results[3]);

			profileBean.setUser(logBean.getName());
			return "profile";
			
		}
	}
	
	
	public String profileEmployer() {
		
		String[] results = profileEmployer.firstProfile(logBean.getName());
		
		if(!results[0].equalsIgnoreCase("no"))
		{
		profileEmployerBean.setCompany(results[0]);
		profileEmployerBean.setLocation(results[1]);
		profileEmployerBean.setTechnology(results[2]);
		profileEmployerBean.setDomain(results[3]);
		profileEmployerBean.setUser(logBean.getName());
			return "profile";
		}
		else
		{
			profileEmployerBean.setUser(logBean.getName());
			return "profile";
		}
	}
	
}
