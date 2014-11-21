/**
 * 
 */
package edu.osu;

import java.io.IOException;
import java.util.logging.*;
import javax.ejb.EJB;

import examples.cse769.EJB.Service.LoginSession;

import examples.cse769.EJB.Service.HelloService;
/**
 * @author Reddiah
 *
 */
public class LoginBean
{


	//private static final Logger log = LoggerFactory.getLogger(LoginBean.class);
	//Logger logger = LoggerFactory.getLogger(LoginBean.class);
    private static Logger logger = Logger.getLogger("edu.osu.LoginBean");
    private static FileHandler fh;
	@EJB 
	private HelloService helloService;
    private String name;
    private String password;
    private String selection;
   
    public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	@EJB private LoginSession loginSession;
	public String userName() {

 	   String[] params = new String[3];
 	   params[0] = name;
 	   params[1] = password;
 	   params[2] = selection;
 	   try {
 		   if(fh == null)
 	   fh = new FileHandler("final.txt");//"mylog.txt"
 	  fh.setFormatter(new SimpleFormatter());
      logger.addHandler(fh);
      logger.setLevel(Level.ALL);
      logger.entering("LoginBean", "userName", params);
 	   }
 		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 	  //logHelperBean.logMe("LoginBean", "userName", "Entering", params);
		//logger.info("an info message");
		//logger.trace("a trace message");
		String res = loginSession.userName(name, password, selection);
		//initializeLog4j("log4j.properties");

		 //Logger.getRootLogger().getLoggerRepository().resetConfiguration(); null pointer
		//BasicConfigurator.resetConfiguration();

		 //LogManager.resetConfiguration();
		 //Enumeration enumLoggers = Logger.getRootLogger().getLoggerRepository().getCurrentLoggers();
		  //LogManager.getRootLogger().setLevel(Level.TRACE);
		   
		   //Level level = new Level();
		   
		    /*FileAppender fapp = new FileAppender();
		   fapp.setName("FileLogger");

		   fapp.setFile("god.txt");
		   fapp.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		   fapp.setThreshold(Level.ALL);//was debug
		   fapp.setImmediateFlush(true);
		   fapp.activateOptions();
		   
		   Logger.getRootLogger().addAppender(fapp);
		   LogManager.getRootLogger().addAppender(fapp);

		   final Logger log = Logger.getRootLogger();
		     Logger log2 = Logger.getRootLogger();
		   


		   Logger log1 = Logger.getLogger(ApplyBean.class.getName());//static
		   //log1.addAppender(fapp);
		   //log1.setLevel(level);
		 log1.warn("warn");
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
	      
	      log2.warn("warn");
			 log2.debug("debug");
			 log2.info("info");
			 log2.error("error");
			 log2.trace("trace");
	      
	      

	     // log.setLevel(Level.WARN);
	     log.info("Hello this is an info message Dingry");
	     
	     
	     */
	      logger.exiting("LoginBean", "userName", res);

		if (res.equalsIgnoreCase("success"))
		{
				
			return selection;
		}
		else if(res.equalsIgnoreCase("fail"))
			return "false";
		
		return "false";
	}
	
	//public String password() {
		//String pass = helloService.password(password);
		//return pass;
	//}
	
	public String getMessage() {
		//String pass = helloService.password(password);
		//return pass;
		return "lol";
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
	   public String getName ()
	    {
	        return name;
	    }


	    public void setName (final String name)
	    {
	        this.name = name;
	    }


	    public String getPassword ()
	    {
	        return password;
	    }


	    public void setPassword (final String password)
	    {
	        this.password = password;
	    }
	
}
