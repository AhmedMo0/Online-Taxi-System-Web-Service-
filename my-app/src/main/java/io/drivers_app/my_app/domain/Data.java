package io.drivers_app.my_app.domain;

import java.util.ArrayList;

public class Data {
	
    public static ArrayList<Person> userList;
    public static ArrayList<Driver> pendingDrivers;
    public static ArrayList<Person> suspended;
   
    public static ArrayList<Trip> tripList;
    public static ArrayList<Driver> driverList;
    
    private static Data instance = null;
    private Person currUser = null;

    
    private Data()
    {
    	userList = new ArrayList<>();
    	pendingDrivers = new ArrayList<>();
    	suspended = new ArrayList<>();
    	tripList = new ArrayList<>();
		driverList = new ArrayList<>();
    	
    	Admin defaultAdmin = new Admin("admin", "admin", "011000000");
    	currUser = defaultAdmin;
    	addUser(defaultAdmin);

    }
    
    
    public Person getCurrentUser()
    {
    	Person tmp = currUser;
    	currUser = null;
    	
    	return tmp;
    }
    
    
    public void addUser(Person p) {
    	
    	for(Person user: userList)
    	{
    		if(user.getUsername().equals(p.getUsername() ) )
    		{
    			System.out.println("This username is taken choose another one!");
    			return;
    		}
    	}
    	
    	
        if(p instanceof Driver)
        {
        	pendingDrivers.add((Driver)p);
            
        }
        else {
        	userList.add(p);
        }
    }




    public static Data getInstance()
    {
    	if(instance == null)
		{
    		instance = new Data();
		}
		
		return instance;
    }

    public int findUser(String username , String pass)
    {
    	Person tmp = null;
    	int num = 0;
    	
    	for (Person user : userList)
    	{
    		if(user.getUsername().equals(username) && user.getPassword().equals(pass))
    		{
    			tmp = user;
    			currUser = user;
    		}
		}
    	
    	if(tmp != null)
    	{
	    	if(tmp instanceof NormalUser)
	    	{
	    		num = 1;
	    	}
	    	else if(tmp instanceof Driver)
	    	{
	    		num = 2;
	    	}
	    	else if(tmp instanceof Admin)
	    	{
	    		num = 3;
	    	}
    	}
    	
    	return num;
    }
    
    public Person getUserByName(String username)
    {
    	Person tmp = null;
    	for(Person user: userList)
    	{
    		if(user.getUsername().equals(username) )
    		{
    			tmp = user;
    		}
    	}
    	
    	return tmp;
    }

    public void deleteTrip(Trip t)
	{
		tripList.remove(t);
		for(Driver d: driverList)
		{
			d.driverOperations.deleteFromAvailabeTrips(t);
		}
		
	}
	
	public void addTrip(Trip t)
	{
		if(tripList.indexOf(t) == -1)
		{
			tripList.add(t);
		}
		
		notifyDrivers(t);
		
	}
	
	public Trip getTrip(String src)
	{
		Trip tmp = null;
		
		for(Trip t: tripList)
		{
			if(t.source.equals(src))
			{
				tmp = t;
			}
		}
		
		return tmp;
	}
	
	public void addDriver(Driver d)
	{
		if(driverList.indexOf(d) == -1)
		{
			driverList.add(d);
		}
	}
	
	public void notifyDrivers(Trip t)
	{
		for(Driver d: driverList)
		{
			d.driverOperations.addToAvailableTrips(t);
		}
	}
	
}