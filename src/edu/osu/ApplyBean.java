/**
 * 
 */
package edu.osu;

import javax.ejb.EJB;

import edu.osu.WelcomeBean;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

import examples.cse769.EJB.Service.JobApply;
import examples.cse769.EJB.Service.HelloService;
import examples.cse769.EJB.Service.JobListing;

/**
 * @author Reddiah
 *
 */
public class ApplyBean
{
	
	private String aid;

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	private LoginBean logBean;
	public LoginBean getLogBean() {
		return logBean;
	}

	public void setLogBean(LoginBean logBean) {
		this.logBean = logBean;
	}

	private WelcomeBean welBean;

	public WelcomeBean getWelBean() {
		return welBean;
	}

	public void setWelBean(WelcomeBean welBean) {
		this.welBean = welBean;
	}

	@EJB 
	private HelloService helloService;
	@EJB
	private JobListing jobListing;
	
	@EJB
	private JobApply jobApply;

	public String apply() {
		

		//String aid = welBean.getAid();
		
		long aidl = Long.valueOf(aid).longValue();;//change
		String results = jobApply.applyJob(aidl, logBean.getName());
		
		//String[] results = helloService.search(title, location);//position String non array hmmmmm
		
		
		 //System.setProperty("log4j.debug", "");
		
		 //initializeLog4j("log4j.properties");
		 
	    // PropertyConfigurator.configure("log5j.properties");

		  //final Logger log = Logger.getRootLogger();

		   //Logger log1 = Logger.getLogger(ApplyBean.class.getName());//static
		  
		   //Logger log2 = LogFactory.getLogger(WelcomeBean.class.getName());//static

		 //final Logger log2 = Logger.getLogger()//static
		 /*log1.warn("warn");
		 log1.debug("debug");
		 log1.info("info");
		 log1.error("error");
		 log1.fatal("fatal");

		 log.info("Hello this is an info message Dingry");

		 log.warn("Hello this is an warn message Dingry");
		 log.debug("Hello this is an debug message Dingry");
		 log.trace("Trace Message!");
	      log.error("Error Message!");
	      log.fatal("Fatal Message!");

	     // log.setLevel(Level.WARN);
	     log.info("Hello this is an info message Dingry");*/
		
	    //BasicConfigurator.configure(); chk later

	
		//String[] results = helloService.search(title, location);//position String non array hmmmmm
		
		if(results.equalsIgnoreCase("no"))
			return "false";
		
		else
		{
			return "true";		
		}


	}
	
	
	
	/*public void initializeLog4j(String propertyfileName)
	{
		String url;
	      try {System.out.println("looking for property file with name "+
	propertyfileName);
	      url = this.getClass().getClassLoader().getResource(propertyfileName).toString();
	  } 
	
	catch (Exception e) {
	   System.out.println("Error: Could not find property file with name"+propertyfileName+".");
	      throw new RuntimeException("Could not initialize log4j");
	   }
	  System.out.println("found it: "+url);
	   if (url.indexOf(".jar!") == -1) {
	     url = url.substring(url.indexOf(":")+1);
	     System.out.println("using watchdog on url: "+url);
	     PropertyConfigurator.configureAndWatch(url);
	    } else {
	      System.out.println("just configure, no watching. url: "+url);
	     PropertyConfigurator.configure(url);
	    }
	}*/


	
}
