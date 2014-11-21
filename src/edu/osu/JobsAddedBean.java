/**
 * 
 */
package edu.osu;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;


import examples.cse769.EJB.Service.JobApply;
import examples.cse769.EJB.Service.HelloService;
import examples.cse769.EJB.Service.JobListing;
import examples.cse769.EJB.Service.JobsApplied;
import examples.cse769.EJB.Service.JobsAdded;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * @author Reddiah
 *
 */
public class JobsAddedBean
{
	//private static final Logger log = LoggerFactory.getLogger(JobsAddedBean.class);

	private LoginBean logBean;

	
	public LoginBean getLogBean() {
		return logBean;
	}


	public void setLogBean(LoginBean logBean) {
		this.logBean = logBean;
	}


	@EJB
	private JobsAdded jobsAdded;


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	
    private String user;
    private List<List<String>> jobs = new ArrayList<List<String>>();


	public List<List<String>> getJobs() {
		return jobs;
	}


	public void setJobs(List<List<String>> jobs) {
		this.jobs = jobs;
	}

	public String jobsAdded() {
		
		
		 user = logBean.getName();
		 jobs = jobsAdded.jobsAdded(user);
				
		if(jobs.isEmpty())
			return "false";
		
		else
		{return "true";}

	}
	
}
