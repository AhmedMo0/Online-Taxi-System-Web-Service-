package io.drivers_app.my_app.operations;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import io.drivers_app.my_app.domain.Admin;
import io.drivers_app.my_app.domain.Data;
import io.drivers_app.my_app.domain.Driver;
import io.drivers_app.my_app.domain.Event;
import io.drivers_app.my_app.domain.NormalUser;
import io.drivers_app.my_app.domain.Person;
import io.drivers_app.my_app.domain.Trip;

public class DataOperations {
	
	Data data;
	
	public DataOperations(Data data)
	{
		this.data = data;
	}
	

	public Person getCurrentUser()
    {
    	Person tmp = data.currUser;
    	data.currUser = null;
    	
    	return tmp;
    }
    
	
	public ArrayList<NormalUser> getUsers()
    {
    	ArrayList<NormalUser> tmp = new ArrayList<>();
    	
    	for(Person u: data.userList)
    	{
    		if(u instanceof NormalUser)
    		{
    			tmp.add( (NormalUser)u);
    		}
    	}
    	
    	return tmp;
    }
    
    public Long addUser(Person p) {
    	
    	for(Person user: data.userList)
    	{
    		if(user.getUsername().equals(p.getUsername() ) || p.getUsername().equals("admin"))
    		{
    			System.out.println("This username is taken choose another one!");
    			return -1L;
    		}
    	}
    	
    	
        if(p instanceof Driver)
        {
        	data.pendingDrivers.add((Driver)p);
            
        }
        else {
        	data.userList.add(p);
        }
        
        return 1L;
    }
    
    public int findUser(String username , String pass)
    {
    	Person tmp = null;
    	int num = 0;
    	
    	for (Person user : data.userList)
    	{
    		if(user.getUsername().equals(username) && user.getPassword().equals(pass))
    		{
    			tmp = user;
    			data.currUser = user;
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
    	for(Person user: data.userList)
    	{
    		if(user.getUsername().equals(username) )
    		{
    			tmp = user;
    			
    			return tmp;
    		}
    	}
    	
    	if(tmp == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
    	}
    	
    	return tmp;
    }

    public void deleteTrip(Trip t)
	{
    	data.tripList.remove(t);
		for(Driver d: data.driverList)
		{
			d.driverOperations.deleteFromAvailabeTrips(t);
		}
		
	}
	
	public void addTrip(Trip t)
	{
		if(data.tripList.indexOf(t) == -1)
		{
			data.tripList.add(t);
		}
		
		notifyDrivers(t);
		
	}
	
	public Trip getTrip(String src)
	{
		Trip tmp = null;
		
		for(Trip t: data.tripList)
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
		if(data.driverList.indexOf(d) == -1)
		{
			data.driverList.add(d);
		}
	}
	
	public void notifyDrivers(Trip t)
	{
		for(Driver d: data.driverList)
		{
			d.driverOperations.addToAvailableTrips(t);
		}
	}
	
	public boolean areaHasDiscount(String areaName)
	{
		boolean f = false;
		for(String s: data.destAreaWithDiscount)
		{
			if(s.equals(areaName))
			{
				f = true;
				break;
			}
		}
		
		return f;
	}
	
	public void addEvent(Event event)
	{
		if(data.eventList.indexOf(event) == -1)
		{
			data.eventList.add(event);
		}
	}
	
	public ArrayList<Event> getEventList()
	{
		return data.eventList;
	}
	
}
