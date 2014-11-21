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


/**
 * @author Reddiah
 *
 */
public class JobsAppliedBean
{
	private List<List<String>> jobs = new ArrayList<List<String>>();
	
	public List<List<String>> getJobs() {
		return jobs;
	}


	public void setJobs(List<List<String>> jobs) {
		this.jobs = jobs;
	}


	private LoginBean logBean;
	public LoginBean getLogBean() {
		return logBean;
	}


	public void setLogBean(LoginBean logBean) {
		this.logBean = logBean;
	}


	@EJB
	private JobsApplied jobsApplied;

	public String jobsApplied() {
	    
		jobs = jobsApplied.jobsApplied(logBean.getName());
				
		if(jobs.isEmpty())
			return "false";
		
		else
		{return "true";}

	}


	
}
