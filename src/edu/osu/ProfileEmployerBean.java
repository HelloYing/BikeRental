/**
 * 
 */
package edu.osu;

import javax.ejb.EJB;



import examples.cse769.EJB.Service.HelloService;
import examples.cse769.EJB.Service.JobApply;
import examples.cse769.EJB.Service.JobListing;
import examples.cse769.EJB.Service.ProfileEmployer;


/**
 * @author Reddiah
 *
 */
public class ProfileEmployerBean
{

	private LoginBean logBean;

	public LoginBean getLogBean() {
		return logBean;
	}
	public void setLogBean(LoginBean logBean) {
		this.logBean = logBean;
	}

	@EJB 
	private ProfileEmployer profileEmployer;

	private CandidateBean candidateBean;
	
	public CandidateBean getCandidateBean() {
		return candidateBean;
	}
	public void setCandidateBean(CandidateBean candidateBean) {
		this.candidateBean = candidateBean;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
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

	private String company;
	private String location;
    private String domain;
    private String technology;
    private String user;
    
    
public String update() {
	    
		
		String ret = profileEmployer.profile(company, location, 
								domain, technology, logBean.getName());
		if(ret.equalsIgnoreCase("true"))
		return "update";
		else
			return "fail";

	}

	
}
