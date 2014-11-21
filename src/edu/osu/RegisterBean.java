/**
 * 
 */
package edu.osu;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import examples.cse769.EJB.Service.HelloService;

/**
 * @author Reddiah
 *
 */
public class RegisterBean
{
	@EJB 
	private HelloService helloService;
    private String name;
    private String password;
    private String valid;
    //radio
    private String selection = "Employee";
    //radio
    
    //test
    private List<List<String>> jobs = new ArrayList<List<String>>();
    

	public List<List<String>> getJobs() {
		if(jobs.size() == 0)
		{
		List<String> job1 = new ArrayList<String>();
		List<String> job2 = new ArrayList<String>();
		List<String> job3 = new ArrayList<String>();

		job1.add("Developer");
		job1.add("Java");
		job1.add("80000");
		
		job2.add("Coder");
		job2.add("C");
		job2.add("60000");
		
		job3.add("Manager");
		job3.add("Arch");
		job3.add("120000");
		
		jobs.add(job1);
		jobs.add(job2);
		jobs.add(job3);

	}
		return jobs;

	}

	public void setJobs(List<List<String>> jobs) {
		this.jobs = jobs;
	}
    
    

    //test







	//radio
    public String getSelection() {
		return selection;
	}


	public void setSelection(String selection) {
		this.selection = selection;
	}
//radio

	public String getValid() {
		return valid;
	}


	public void setValid(String valid) {
		this.valid = valid;
	}


	public String getName ()
    {
        return name;
    }


    public void setName (final String name)
    {
		if(name.equalsIgnoreCase("!") || name.equalsIgnoreCase("@") || name.equalsIgnoreCase("#")
				|| name.equalsIgnoreCase("$") || name.equalsIgnoreCase("%") || name.equalsIgnoreCase("^")
				|| name.equalsIgnoreCase("&") || name.equalsIgnoreCase("*") || name.equalsIgnoreCase("(")
				|| name.equalsIgnoreCase(")") || name.equalsIgnoreCase("_") || name.equalsIgnoreCase("+")
				|| name.equalsIgnoreCase(""))
			setValid("Not a Valid User Name");
		else
			setValid("Valid User Name");


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


	public String userName() {
		if((name.isEmpty() != true) && (password.isEmpty()!= true)) {
		String ret = helloService.userName(name, password, selection);
		//helloService.password(password);
		if(!ret.equalsIgnoreCase("Exists"))
		return "true";
		else 
			return "Exists";
		}
		
		else return "false";
	}
	
}
